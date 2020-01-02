package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Bucket;

public interface BucketService {
    Bucket addItem(Long bucketId, Long itemId);
}
