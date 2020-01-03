package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

import java.util.List;

//@Service
public class BucketServiceImpl implements BucketService {
//    @Dao
    private BucketDao bucetDao;
    private ItemDao itemDao;

//    @Override
//    public Bucket addItem(Long bucketId, Long itemId) {
//        Bucket bucket = bucetDao.get(bucketId);
//        Item item = itemDao.get(itemId);
//        bucket.getItems().add(item);
//        return bucetDao.update(bucket);
//    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        System.out.println("item has some ID");
        System.out.println("bucket has some ID");


        // if bucket already exist -> throw the exception
        bucket.getItems().add(item);
        bucetDao.update(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {

    }

    @Override
    public void clear(Bucket bucket) {

    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return null;
    }
}
