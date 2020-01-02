package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.bd.Storage;
import mate.academy.internetshop.model.Bucket;

public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
        return null;
    }

    @Override
    public Bucket get(Long bucketId) {
        return Storage.buckets
                .stream().filter(b -> b.getId().equals(bucketId))
                .findFirst().orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with id + " + bucketId));
    }

    @Override
    public Bucket update(Bucket bucket) {
        return null;
    }
}
