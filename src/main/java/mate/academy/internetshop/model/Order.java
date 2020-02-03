package mate.academy.internetshop.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private Long id;
    private User user;
    private List<Item> items;

    public Order () {}

    public Order(User user, List<Item> items) {
        this.user = user;
        this.items = items;
    }

    public Order setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public Order(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", user=" + user + ", items=" + items + '}';
    }
}

