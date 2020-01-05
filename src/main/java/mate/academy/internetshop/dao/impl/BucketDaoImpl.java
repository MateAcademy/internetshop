package mate.academy.internetshop.dao.impl;

import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.service.idgenerators.BucketIdGenerator;

@Dao
public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
        bucket.setId(BucketIdGenerator.getId());
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        return Storage.buckets
                .stream().filter(b -> b.getId().equals(bucketId))
                .findFirst();
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (bucket.getId().equals(Storage.buckets.get(i).getId())) {
                Storage.buckets.remove(i);
            }
        }
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public boolean delete(Long bucketId) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (bucketId.equals(Storage.buckets.get(i).getId())) {
                Storage.buckets.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Bucket bucket) {
        return Storage.buckets.remove(bucket);
    }
}
