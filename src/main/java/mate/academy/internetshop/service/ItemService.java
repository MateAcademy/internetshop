package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.Item;

public interface ItemService {
    List<Item> getAllItems();

    Item create(Item item);

    Optional<Item> get(Long id);

    Item update(Item item);

    void deleteByID(Long id);

    void delete(Item item);
}
