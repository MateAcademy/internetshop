package mate.academy.internetshop.model;

import mate.academy.internetshop.service.idgenerators.OrderIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<Item> items = new ArrayList<>();

//    public Order(User user) {
//        this.userId = user.getGeneratedId();
//    }

    public Order(Long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
        this.id = OrderIdGenerator.getGeneratedId();
    }

    public List<Item> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", items=" + items +
                '}';
    }
}

