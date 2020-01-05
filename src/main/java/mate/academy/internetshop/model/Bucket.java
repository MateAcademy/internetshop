package mate.academy.internetshop.model;

import java.util.List;

public class Bucket {

    private Long id;
    private List<Item> items;
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
