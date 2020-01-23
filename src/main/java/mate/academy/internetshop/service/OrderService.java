package mate.academy.internetshop.service;

import java.util.List;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */
public interface OrderService {
    public List<Order> getAll();

    public  Order completeOrder(Bucket bucket);

    Order completeOrder(List<Item> items, User user);

//    List<Order> getAllOrdersForUser(Long userId);
//    List<Order> getAllOrdersForUser(User user); у богдана

    Order create(Order order);

    Order get(Long orderId);

    Order update(Order order);

    boolean delete(Long orderId);

    boolean delete(Order order);
}
