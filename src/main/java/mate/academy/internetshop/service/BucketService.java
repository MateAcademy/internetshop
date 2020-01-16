package mate.academy.internetshop.service;

import java.util.List;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

public interface BucketService {

    Bucket getByUserId(Long userId);

    void addItem(Bucket bucket, Item item);

    Bucket addItem(Long bucketId, Long itemId);

    Bucket create(Bucket bucket);

    List<Item> getAllItems(Bucket bucket);

    Bucket get(Long bucketId);

    Bucket update(Bucket bucket);

    boolean delete(Long bucketId);

    boolean delete(Bucket bucket);

    void deleteItem(Bucket bucket, Item item);

    public List<Bucket> getAll();
}
