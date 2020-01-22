package mate.academy.internetshop.model;

import mate.academy.internetshop.service.idgenerators.ItemIdGenerator;

public class Item {

    private Long id;
    private String name;
    private Double price;
    private String description;

    public Item() {
        id = ItemIdGenerator.getGeneratedId();
    }

    public Item(String name, Double price, String description) {
//        this();
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Item(Long id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\''
                + ", price=" + price + ", description='" + description + '\'' + '}';
    }
}
