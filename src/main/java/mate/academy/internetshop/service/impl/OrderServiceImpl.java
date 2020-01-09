package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

/**
 * @author Sergey Klunniy
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order order = new Order(user.getId(), items);
        Storage.orders.add(order);
        return order;
    }

    @Override
    public  Order completeOrder(Bucket bucket) {
        Order order = new Order(bucket.getUserId(), bucket.getItems());
        Storage.orders.add(order);
        return order;
    }

    @Override
    public List<Order> getAllOrdersForUser(Long userId) {
        return Storage.orders.stream()
                .filter(x -> x.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long orderId) {
        return orderDao.get(orderId).get();
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Long orderId) {
        return orderDao.delete(orderId);
    }

    @Override
    public boolean delete(Order order) {
        return orderDao.delete(order);
    }
}
