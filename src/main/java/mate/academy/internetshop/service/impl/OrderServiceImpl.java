package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
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
        Order order = new Order(user).setItems(items);
        Storage.orders.add(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders.stream()
                .filter(x -> x.getUserId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Optional<Order> get(Long orderId) {
        return orderDao.get(orderId);
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
