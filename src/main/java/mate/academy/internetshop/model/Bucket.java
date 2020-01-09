package mate.academy.internetshop.model;

import mate.academy.internetshop.service.idgenerators.BucketIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Bucket {

    private Long id;
    private Long userId;
    private List<Item> items = new ArrayList<>();

    public Bucket(Long id) {
        this.userId = id;
        this.id = BucketIdGenerator.getGeneratedId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
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
