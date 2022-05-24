package lv.bluma.ordersys.model.outgoing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"itemId", "amount"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItem {

    public OrderItem() {
    }

    public OrderItem(String itemId, Integer amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    @JsonProperty("itemId")
    private String itemId;

    @JsonProperty("amount")
    private Integer amount;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
