package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

/**
 * @author Sergey Klunniy
 */
@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean deleteById(Long entityId) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (entityId.equals(Storage.orders.get(i).getId())) {
                Storage.orders.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long orderId) {
        return Storage.orders.stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst();
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (order.getId().equals(Storage.orders.get(i).getId())) {
                Storage.orders.remove(i);
            }
        }
        Storage.orders.add(order);
        return order;
    }

    @Override
    public boolean delete(Order order) {
        return Storage.orders.remove(order);
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }
}
