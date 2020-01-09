package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<Item> items = new ArrayList<>();

    public Order(User user) {
        this.userId = user.getId();
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
}

