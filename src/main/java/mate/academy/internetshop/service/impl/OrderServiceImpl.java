package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Basket;
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

    @Inject
    private static UserDao userDao;

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
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
    public Order completeOrder(List<Item> items, User user) {
        Order order = new Order(user, items);
        Storage.orders.add(order);
        return order;
    }

    @Override
    public  Order completeOrder(Basket basket) {
        Optional<User> optUser = userDao.get(basket.getUserId());
        User user = new User();
        if (optUser.isPresent()) {
            user = optUser.get();
        } else {
            user = null;
        }
        Order order = new Order(user, basket.getItems());
        Storage.orders.add(order);
        return order;
    }

    @Override
    public List<Order> getAllOrdersForUser(User user) {
        return Storage.orders.stream()
                .filter(x -> x.equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Long orderId) {
        return orderDao.deleteById(orderId);
    }

    @Override
    public boolean delete(Order order) {
        return orderDao.delete(order);
    }
}
