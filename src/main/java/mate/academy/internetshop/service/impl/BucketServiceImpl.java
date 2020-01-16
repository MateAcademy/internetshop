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
    private static BucketDao bucketDao;
    @Inject
    private static ItemDao itemDao;

    @Override
    public List<Bucket> getAll() {
        return bucketDao.getAll();
    }

    @Override
    public Bucket getByUserId(Long userId) {
        if (bucketDao.getByUserId(userId).isPresent()) {
            return bucketDao.getByUserId(userId).get();
        }
        return new Bucket(userId);
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        bucket.addItem(item);
        bucketDao.update(bucket);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId).get();
        Optional<Item> optItem = itemDao.get(itemId);
        if (optItem.isPresent()) {
            Item item = optItem.get();
            bucket.getItems().add(item);
            return bucketDao.update(bucket);
        }
        return null;
    }

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucketDao.get(bucket.getUserId()).get().getItems();
    }

    @Override
    public Bucket get(Long id) {
        Optional<Bucket> optBucket = bucketDao.get(id);
        if (optBucket.isPresent()) {
            return optBucket.get();
        }
        return null;
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Long bucketId) {
        return bucketDao.deleteById(bucketId);
    }

    @Override
    public boolean delete(Bucket bucket) {
        return bucketDao.delete(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        bucket.getItems().remove(item);
    }
}
