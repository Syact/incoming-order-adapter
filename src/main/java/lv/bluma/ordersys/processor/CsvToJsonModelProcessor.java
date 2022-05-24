package lv.bluma.ordersys.processor;

import lv.bluma.ordersys.model.outgoing.Order;
import lv.bluma.ordersys.model.outgoing.OrderBatch;
import lv.bluma.ordersys.model.outgoing.OrderItem;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.LinkedList;
import java.util.List;

public class CsvToJsonModelProcessor implements Processor {

    @SuppressWarnings("unchecked")
    @Override
    public void process(Exchange exchange) throws Exception {
        List<List<String>> csv = (List<List<String>>) exchange.getIn().getBody();
        if (csv == null || csv.size() < 2) {
            throw new RuntimeException("No orders present in input CSV file");
        }

        OrderBatch orderBatch = new OrderBatch(
                exchange.getIn().getHeader("CamelFileName", String.class),
                new LinkedList<>()
        );

        List<String>[] csvArray = csv.toArray(new List[0]);
        for (int csvLine = 1; csvLine < csvArray.length; csvLine++) {
            String[] orderData = csvArray[csvLine].toArray(new String[0]);
            Order order = new Order(
                    orderData[0], orderData[1], new LinkedList<>()
            );

            for (int orderDataIndex = 2; orderDataIndex < orderData.length - 1; orderDataIndex += 2) {
                OrderItem orderItem = new OrderItem(
                        orderData[orderDataIndex], Integer.valueOf(orderData[orderDataIndex + 1])
                );
                order.getOrderItems().add(orderItem);
            }
            orderBatch.getOrders().add(order);
        }
        exchange.getIn().setBody(orderBatch);
    }
}
