package lv.bluma.ordersys;

import lv.bluma.ordersys.model.incoming.xml.OrderBatchXml;
import lv.bluma.ordersys.model.outgoing.OrderBatch;
import lv.bluma.ordersys.processor.CsvToJsonModelProcessor;
import lv.bluma.ordersys.processor.XmlToJsonModelProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Component
public class OrderRouting extends RouteBuilder {

    private static final String DIRECT_INCOMING_XML = "direct:incomingXml";
    private static final String DIRECT_INCOMING_CSV = "direct:incomingCsv";

    private static final JacksonDataFormat JSON_DATA_FORMAT = new JacksonDataFormat(OrderBatch.class);
    private static final String NEW_ORDER_BATCH_QUEUE = "rabbitmq://localhost:5672/" +
            "Exchange.OrderSystem.NewOrderBatch?" +
            "queue=Queue.OrderSystem.NewOrderBatch&autoDelete=false";

    @Override
    public void configure() throws Exception {
        setupDeadLetterChannel();
        setupFilePollingContentBasedRouter();
        setupXmlTransformationRoute();
        setupCsvTransformationRoute();
    }

    private void setupFilePollingContentBasedRouter() {
        from("file:src/main/resources/input?noop=true")
                .routeId("route-file-reader")
                .choice()
                    .when(header("CamelFileName").endsWith(".xml"))
                        .log("Reading XML file: ${header.CamelFileName}")
                        .to(DIRECT_INCOMING_XML)
                    .when(header("CamelFileName").endsWith(".csv"))
                        .log("Reading CSV file: ${header.CamelFileName}")
                        .to(DIRECT_INCOMING_CSV)
                    .otherwise()
                        .log("Unknown Extension: ${header.CamelFileName}. Ignoring file.")
                .end();
    }

    private void setupXmlTransformationRoute() throws JAXBException {
        JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
        xmlDataFormat.setContext(JAXBContext.newInstance(OrderBatchXml.class));

        from(DIRECT_INCOMING_XML)
                .unmarshal(xmlDataFormat)
                .process(new XmlToJsonModelProcessor())
                .marshal(JSON_DATA_FORMAT)
                .to(NEW_ORDER_BATCH_QUEUE)
                .end();
    }

    private void setupCsvTransformationRoute() {
        from(DIRECT_INCOMING_CSV)
                .unmarshal().csv()
                .process(new CsvToJsonModelProcessor())
                .marshal(JSON_DATA_FORMAT)
                .to(NEW_ORDER_BATCH_QUEUE)
                .end();
    }

    private void setupDeadLetterChannel() {
        errorHandler(deadLetterChannel(
                "rabbitmq://localhost:5672/Exchange.OrderSystem.DLQ" +
                        "?queue=Queue.OrderSystem.DLQ" +
                        "&autoDelete=false" +
                        "&arg.queue.x-message-ttl=60000")
        );
    }
}
