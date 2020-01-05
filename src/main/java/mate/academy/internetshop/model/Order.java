package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private Long id;
    private List<Item> items;
    private User user;

    public List<Item> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

