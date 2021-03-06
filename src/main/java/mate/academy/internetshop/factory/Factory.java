package mate.academy.internetshop.factory;

import mate.academy.internetshop.dao.BasketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.jdbc.BasketDaoJdbcImpl;
import mate.academy.internetshop.dao.jdbc.ItemDaoJdbcImpl;
import mate.academy.internetshop.dao.jdbc.OrderDaoJdbcImpl;
import mate.academy.internetshop.dao.jdbc.UserDaoJdbcImpl;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;

/**
 * @author Sergey Klunniy
 */
public class Factory {
    private static UserDao userDao;
    private static OrderDao orderDao;
    private static ItemDao itemDao;
    private static BasketDao basketDao;

    private static UserService userService;
    private static OrderService orderService;
    private static ItemService getItemService;
    private static BucketService bucketService;

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoJdbcImpl();
        }
        return userDao;
    }

    public static OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDaoJdbcImpl();
        }
        return orderDao;
    }

    public static ItemDao getItemDao() {
        if (itemDao == null) {
            itemDao = new ItemDaoJdbcImpl();
        }
        return itemDao;
    }

    public static BasketDao getBasketDao() {
        if (basketDao == null) {
            basketDao = new BasketDaoJdbcImpl();
        }
        return basketDao;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    public static ItemService getGetItemService() {
        if (getItemService == null) {
            getItemService = new ItemServiceImpl();
        }
        return getItemService;
    }

    public static BucketService getBucketService() {
        if (bucketService == null) {
            bucketService = new BucketServiceImpl();
        }
        return bucketService;
    }
}
