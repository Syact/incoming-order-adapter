package lv.bluma.ordersys.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TestProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        String body = exchange.getIn().getBody(String.class);
        System.out.println("=======\n" + body + "=======\n");
    }
}
