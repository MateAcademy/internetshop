package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop.service.idgenerators.BucketIdGenerator;

public class Basket {

    private Long id;
    private Long userId;
    private List<Item> items = new ArrayList<>();

    public Basket() {
        this.id = BucketIdGenerator.getGeneratedId();
    }

    public Basket(Long userId) {
        this();
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public String toString() {
        return "Basket{" + "id=" + id + ", userId=" + userId + ", items=" + items + '}';
    }
}
