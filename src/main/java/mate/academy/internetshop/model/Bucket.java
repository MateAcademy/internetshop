package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {

    private Long id;
    private Long userId;
    private List<Item> items = new ArrayList<>();

    public Bucket(User user) {
        this.userId = user.getId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "id=" + id
                + ", userId=" + userId
                + ", items=" + items
                + '}';
    }
}
