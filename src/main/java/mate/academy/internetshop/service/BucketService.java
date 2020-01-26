package mate.academy.internetshop.service;

import java.util.List;

import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Item;

public interface BucketService {

    Basket getByUserId(Long userId);

    void addItem(Basket basket, Item item);

    Basket addItem(Long bucketId, Long itemId);

    Basket create(Basket basket);

    List<Item> getAllItems(Basket basket);

    Basket get(Long bucketId);

    Basket update(Basket basket);

    boolean delete(Long bucketId);

    boolean delete(Basket basket);

    void deleteItem(Basket basket, Item item);

    public List<Basket> getAll();
}
