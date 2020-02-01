package mate.academy.internetshop.dao;

import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */
public interface  OrderDao extends GenericDao<Order, Long> {
    List<Order> getAllOrdersForUser(User user);
}
