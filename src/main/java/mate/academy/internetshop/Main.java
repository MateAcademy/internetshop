package mate.academy.internetshop;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.InjectorAdvanced;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */

public class Main {

    @Inject
    private static BucketDao bucketDao;

    static {
        try {
            InjectorAdvanced.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        User user = new User("Sergei", "mate@gmail.com");
        Bucket bucket = new Bucket(user);
        bucketDao.create(bucket);

        System.out.println(bucket.getId());
    }
}
