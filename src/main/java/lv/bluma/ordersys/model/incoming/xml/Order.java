package lv.bluma.ordersys.model.incoming.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @XmlElement(name = "clientId")
    private String clientId;

    @XmlElement(name = "externalOrderId")
    private String externalOrderId;

    @XmlElementWrapper(name = "orderItems")
    @XmlElement(name = "item")
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
