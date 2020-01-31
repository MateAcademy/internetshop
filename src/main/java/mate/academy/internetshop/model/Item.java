package mate.academy.internetshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mate.academy.internetshop.service.idgenerators.ItemIdGenerator;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Item {

    private Long id;
    private String name;
    private Double price;
    private String description;

    public Item(Long id) {
        this.id = id;
    }

    public Item() {
        id = ItemIdGenerator.getGeneratedId();
    }

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name, Double price, String description) {
        this(name, price);
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\''
                + ", price=" + price + ", description='" + description + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description);
    }
}
