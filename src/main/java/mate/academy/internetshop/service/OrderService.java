package mate.academy.internetshop.service;

import java.util.List;

import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */
public interface OrderService {
    List<Order> getAll();

    Order completeOrder(Basket basket);

    Order completeOrder(List<Item> items, User user);

    List<Order> getAllOrdersForUser(User user);

    Order create(Order order);

    Order get(Long orderId);

    Order update(Order order);

    boolean delete(Long orderId);

    boolean delete(Order order);
}
