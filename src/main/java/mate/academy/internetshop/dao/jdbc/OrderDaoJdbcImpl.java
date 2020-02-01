package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.DataProcessingException;

import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;

import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.DbConnector;
import org.apache.log4j.Logger;

/**
 * @author Sergey Klunniy
 */
@Dao
public class OrderDaoJdbcImpl implements OrderDao {

    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    @Inject
    private static UserDao userDao;

    @Inject
    private static ItemDao itemDao;

    @Override
    public Optional<Order> get(Long idOrder) {
        String sql = "SELECT * FROM shop.orders INNER JOIN shop.orders_items ON "
                + "orders.order_id = orders_items.order_id "
                + "WHERE orders.order_id = ?;";
        try (Connection connection = DbConnector.connect();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, idOrder);
            ResultSet rs = statement.executeQuery();

            Long orderId = null;
            Long userId = null;
            List<Long> items = new ArrayList<>();
            while (rs.next()) {
                orderId = rs.getLong("order_id");
                userId = rs.getLong("user_id");
                items.add(rs.getLong("item_id"));
            }
            List<Item> itemsFromBd = new ArrayList<>();
            for (Long i : items) {
                if (itemDao.get(i).isPresent()) {
                    itemsFromBd.add(itemDao.get(i).get());
                }
            }
            User user = null;
            Optional<User> optUser = userDao.get(userId);
            if (optUser.isPresent()) {
                user = optUser.get();
            }
            Order order = new Order(orderId, user, itemsFromBd);
            return Optional.of(order);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get order by id in db ", e);
        }
    }

    @Override
    public Order create(Order order) {
        String sql = "INSERT INTO shop.orders (user_id) VALUES (?);";
        try (Connection connection = DbConnector.connect();
                PreparedStatement st = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)) {
            st.setLong(1, order.getUser().getId());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                Long orderId = rs.getLong(1);
                order.setId(orderId);
            }
            addItems(order, order.getItems());
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create order ", e);
        }
        return order;
    }

    private void addItems(Order order, List<Item> items) {
        String sql = "INSERT INTO shop.orders_items (order_id, item_id) VALUES (?, ?);";
        changeOrderItems(order, items, sql);
    }

    private void changeOrderItems(Order order, List<Item> items, String sql) {
        try (Connection connection = DbConnector.connect();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Item item : items) {
                statement.setLong(1, order.getId());
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't change order items ", e);
        }
    }

    @Override
    public Order update(Order order) {
        String query = "UPDATE shop.orders SET user_id = ? WHERE order_id = ?;";
        try (Connection connection = DbConnector.connect();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getUser().getId());
            statement.setLong(2, order.getId());
            statement.executeUpdate();
            deleteAllItems(order);
            addItems(order, order.getItems());
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update order ", e);
        }
        return order;
    }

    private List<Item> getItems(Order order) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT i.item_id, name, price, description FROM shop.items i "
                + "JOIN shop.orders_items oi ON i.item_id = oi.item_id AND order_id = ?";
        try (Connection connection = DbConnector.connect();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long itemId = rs.getLong("i.item_id");
                String itemName = rs.getString("name");
                Double itemPrice = rs.getDouble("price");
                String itemDescription = rs.getString("description");
                Item item = new Item(itemName, itemPrice, itemDescription);
                item.setId(itemId);
                items.add(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get items in order ", e);
        }
        return items;
    }

    private void deleteItems(Order order, List<Item> items) {
        String query = "DELETE FROM shop.orders_items WHERE order_id = ? AND item_id = ?;";
        changeOrderItems(order, items, query);
    }

    private void deleteAllItems(Order order) {
        String query = "DELETE FROM shop.orders_items WHERE order_id = ?;";
        try (Connection connection = DbConnector.connect();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete all items ", e);
        }
    }

    @Override
    public boolean deleteById(Long orderId) {
        Optional<Order> optOrder = get(orderId);
        if (optOrder.isPresent()) {
            return delete(optOrder.get());
        }
        return false;
    }

    @Override
    public boolean delete(Order order) {
        deleteItems(order, order.getItems());
        String query = "DELETE FROM shop.orders WHERE order_id = ?;";
        try (Connection connection = DbConnector.connect();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order in db ", e);
        }
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM shop.orders;";
        try (Connection connection = DbConnector.connect();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            return getOrders(rs);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all orders in db", e);
        }
    }

    @Override
    public List<Order> getAllOrdersForUser(User user) {
        String sql = "SELECT * FROM shop.orders  WHERE user_id = ?;";
        try (Connection connection = DbConnector.connect();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, user.getId());
            ResultSet rs = statement.executeQuery();
            return getOrders(rs);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all orders for user in db ", e);
        }
    }

    private List<Order> getOrders(ResultSet rs) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            Long orderId = rs.getLong("order_id");
            Long userId = rs.getLong("user_id");
            List<Item> items = new ArrayList<>();
            Order order = new Order(orderId, userDao.get(userId).get(), items);
            items.addAll(getItems(order));
            order.setItems(items);
            orders.add(order);
        }
        return orders;
    }
}
