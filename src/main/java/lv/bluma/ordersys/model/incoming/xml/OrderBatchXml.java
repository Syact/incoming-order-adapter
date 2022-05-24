package lv.bluma.ordersys.model.incoming.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderBatchXml {

    @XmlElement(name = "batchId")
    private String batchId;

    @XmlElement(name = "order")
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
