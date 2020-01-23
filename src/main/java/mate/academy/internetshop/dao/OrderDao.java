package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface  OrderDao extends GenericDao<Order, Long> {

    List<Order> getAllOrdersForUser(User user);
}
