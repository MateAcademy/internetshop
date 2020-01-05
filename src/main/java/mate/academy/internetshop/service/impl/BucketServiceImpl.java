package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private BucketDao bucketDao;
    @Inject
    private ItemDao itemDao;

    @Override
    public void addItem(Bucket bucket, Item item) {
        System.out.println("item has some ID");
        System.out.println("bucket has some ID");
        bucket.getItems().add(item);
        bucketDao.update(bucket);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId).get();
        Item item = itemDao.get(itemId).get();
        bucket.getItems().add(item);
        return bucketDao.update(bucket);
    }

    @Override
    public void create(Bucket bucket) {
        bucketDao.create(bucket);
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucket.getItems();
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Long bucketId) {
        return bucketDao.delete(bucketId);
    }

    @Override
    public boolean delete(Bucket bucket) {
        return bucketDao.delete(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        List<Item> items = getAllItems(bucket);
        items.remove(item);
        bucket.setItems(items);
    }
}
