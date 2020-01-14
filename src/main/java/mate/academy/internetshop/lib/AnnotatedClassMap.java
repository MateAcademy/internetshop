package mate.academy.internetshop.lib;

import java.util.HashMap;
import java.util.Map;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.factory.Factory;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

/**
 * @author Sergey Klunniy
 */
public class AnnotatedClassMap {
    private static final Map<Class, Object> classMap = new HashMap<>();

    static {
        classMap.put(UserDao.class, Factory.getUserDao());
        classMap.put(OrderDao.class, Factory.getOrderDao());
        classMap.put(ItemDao.class, Factory.getItemDao());
        classMap.put(BucketDao.class, Factory.getBucketDao());

        classMap.put(UserService.class, Factory.getUserService());
        classMap.put(OrderService.class, Factory.getOrderService());
        classMap.put(ItemService.class, Factory.getGetItemService());
        classMap.put(BucketService.class, Factory.getBucketService());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }

}
