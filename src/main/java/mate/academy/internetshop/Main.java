package mate.academy.internetshop;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.model.Bucket;

/**
 * @author Sergey Klunniy
 */
public class Main {
    private static BucketDao bucketDao;
    public static void main(String[] args) {
        Bucket bucket = new Bucket();
        Bucket bucketWithId = bucketDao.create(bucket);
    }
}
