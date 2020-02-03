package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mate.academy.internetshop.service.idgenerators.BucketIdGenerator;

@Getter
@Setter
@AllArgsConstructor
public class Basket {

    private Long id;
    private Long userId;
    private List<Item> items = new ArrayList<>();

    public Basket() { }

    public Basket(Long userId) {
        this.userId = userId;
    }

    public Basket(Long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Basket{" + "id=" + id + ", userId=" + userId + ", items=" + items + '}';
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}
