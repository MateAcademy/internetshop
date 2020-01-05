package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.Item;

public interface ItemDao {
    Item create(Item item);

    Optional<Item> get(long id);

    List<Item> getAllItems();

    Item update(Item item);

    boolean delete(Long id);

    boolean delete(Item item);
}
