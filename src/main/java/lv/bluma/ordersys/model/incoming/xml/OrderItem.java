package lv.bluma.ordersys.model.incoming.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrderItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItem {

    @XmlElement(name = "itemId")
    private String itemId;

    @XmlElement(name = "amount")
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
