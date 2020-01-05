package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketService {

    void addItem(Bucket bucket, Item item);

    Bucket addItem(Long bucketId, Long itemId);

    void create(Bucket bucket);

    List<Item> getAllItems(Bucket bucket);

    Optional<Bucket> get(Long bucketId);

    Bucket update(Bucket bucket);

    boolean delete(Long bucketId);

    boolean delete(Bucket bucket);

    void deleteItem(Bucket bucket, Item item);
}
