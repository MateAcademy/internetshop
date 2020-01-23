package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import mate.academy.internetshop.service.idgenerators.OrderIdGenerator;

@Getter
@Setter
public class Order {
    private Long id;
    private User user;
    private List<Item> items;

    public Order(User user, List<Item> items) {
        this.user = user;
        this.items = items;
    }

    public Order(Long id, User user, List<Item> items) {
        this.id = id;
        this.user = user;
        this.items = items;
    }

    public Order setItems(List<Item> items) {
        this.items = items;
        return this;
    }

}

