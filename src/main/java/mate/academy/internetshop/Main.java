package mate.academy.internetshop;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.InjectorAdvanced;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

/**
 * @author Sergey Klunniy
 */

public class Main {

    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static UserService userService;

    static {
        try {
            InjectorAdvanced.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        User user = new User("Sergei", "mate@gmail.com");
        User user2 = new User("Roma", "itlabs@gmail.com");
        userService.create(user);
        userService.create(user2);
        System.out.println(Storage.users);
        userService.delete(user2);
        System.out.println(Storage.users);

        Bucket bucket = new Bucket(user);
        bucketDao.create(bucket);

        System.out.println(bucket.getId());
    }
}
