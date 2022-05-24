package lv.bluma.ordersys.model.outgoing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"clientId", "externalOrderId", "orderItems"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    public Order() {
    }

    public Order(String clientId, String externalOrderId, List<OrderItem> orderItems) {
        this.clientId = clientId;
        this.externalOrderId = externalOrderId;
        this.orderItems = orderItems;
    }

    @JsonProperty("clientId")
    private String clientId;

    @JsonProperty("externalOrderId")
    private String externalOrderId;

    @JsonProperty("orderItems")
    private List<OrderItem> orderItems;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
