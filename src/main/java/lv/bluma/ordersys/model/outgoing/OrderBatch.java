package lv.bluma.ordersys.model.outgoing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"batchId", "orders"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderBatch {

    public OrderBatch() {
    }

    public OrderBatch(String batchId, List<Order> orders) {
        this.batchId = batchId;
        this.orders = orders;
    }

    @JsonProperty("batchId")
    private String batchId;

    @JsonProperty("orders")
    private List<Order> orders;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
