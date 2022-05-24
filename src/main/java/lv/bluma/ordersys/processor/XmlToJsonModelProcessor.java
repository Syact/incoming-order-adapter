package lv.bluma.ordersys.processor;

import lv.bluma.ordersys.model.incoming.xml.OrderBatchXml;
import lv.bluma.ordersys.model.outgoing.Order;
import lv.bluma.ordersys.model.outgoing.OrderBatch;
import lv.bluma.ordersys.model.outgoing.OrderItem;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class XmlToJsonModelProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        OrderBatchXml xmlBody = exchange.getIn().getBody(OrderBatchXml.class);
        if (xmlBody == null) {
            return;
        }

        OrderBatch jsonBody = new OrderBatch(xmlBody.getBatchId(), new LinkedList<>());
        if (xmlBody.getOrders() != null) {
            List<Order> jsonOrders = xmlBody.getOrders().stream()
                    .map(xmlOrder -> new Order(
                            xmlOrder.getClientId(),
                            xmlOrder.getExternalOrderId(),
                            xmlOrder.getOrderItems() != null
                                    ? xmlOrder.getOrderItems().stream()
                                        .map(xmlOrderItem -> new OrderItem(
                                            xmlOrderItem.getItemId(),
                                            xmlOrderItem.getAmount()))
                                        .collect(Collectors.toList())
                                    : null
                    )).collect(Collectors.toList());
            jsonBody.setOrders(jsonOrders);

            exchange.getIn().setBody(jsonBody);
        }
    }
}
