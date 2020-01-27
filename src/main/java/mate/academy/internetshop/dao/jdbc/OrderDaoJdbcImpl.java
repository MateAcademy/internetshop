package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.DbConnector;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static void main(String[] args) {
//        User user = new User(7L, "Slavik", "f", "f");
//        List<Item> items = new ArrayList<>();
//        items.add(new Item(1L, "laptop", 12000.0, "Ukraine"));
//        items.add(new Item(2L, "ipad", 23000.0, "USA"));
//        Order order = new Order(user, items);

        OrderDaoJdbcImpl ojdbc = new OrderDaoJdbcImpl();
        Optional<Order> optOrder = ojdbc.get(14L);
        if (optOrder.isPresent()) {
            System.out.println(optOrder.get());
        }
    }

    @Override
    public Order create(Order order) {
        String sql = "INSERT INTO shop.orders (user_id) VALUES (?);";

        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUser().getId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                Long orderId = rs.getLong(1);
                order.setId(orderId);
            }
            addItems(order, order.getItems());
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String sql = "SELECT * FROM shop.orders INNER JOIN orders_items ON orders.order_id = orders_items.order_id " +
                "WHERE shop.orders.order_id = ?;";
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            Long orderId, userId;
            List<Long> items = new ArrayList<>();

            if (rs.next()) {
                orderId = rs.getLong("order_id");
                userId = rs.getLong("user_id");
                items.add(rs.getLong("item_id"));
            } else {
                return Optional.empty();
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

        }
        return Optional.empty();
    }



    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long entityId) {
        return false;
    }

    @Override
    public boolean delete(Order entity) {
        return false;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public List<Order> getAllOrdersForUser(User user) {
        return null;
    }


//    @Override
//    public void addOrder(Order order) {
//        try (Connection connection = DbConnector.connect()) {
//            String sql = String.format(ADD_ORDER_IN_DB, order.getFirstName, order.get..
//                    ord.get ...)
//            Statement statement = connection.createStatement();
//
//            logger.info("Order" + order + " added in DB.");
//        } catch (SQLException e) {
//            logger.warn("Order" + order + " can't be added in DB.");
//        }
//
//
//    }
//
//    @Override
//    public Optional<Order> get(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Order update(Order entity) {
//        return null;
//    }
//
//    @Override
//    public boolean deleteById(Long entityId) {
//        return false;
//    }
//
//    @Override
//    public boolean delete(Order entity) {
//        return false;
//    }
//
//    @Override
//    public List<Order> getAll() {
//        List<Order> orders = new ArrayList<>();
//        try (Connection connection = DbConnector.connect()) {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(GET_ALL_ORDERS);
//
//
//            while (resultSet.next()) {
//                Long userId = resultSet.getLong("id");
//                Long basketId = resultSet.getLong(basket_id"");
//
//
//                if (basketId.equals(tempBasketID)) {
//
//                } else {
//
//                    Basket basket = new Basket(basketId);
//                    basket.getItems().add(new Item(
//                            resultSet.getLong("product_id"),
//                            resultSet.getString("name"),
//                            resultSet.getString("description"),
//                            resultSet.getDouble("price")
//                    ));
//                }
//            }
//        } catch (SQLException e) {
//            logger.warn("Can't get all orders from db");
//        }
//
//
//        return null;
//    }
//
//    @Override
//    public List<Order> getAllOrdersForUser(User user) {
//        String getOrdersQuery = "SELECT orders.order_id, orders.user_id, items.item_id, items.name, items.price" +
//                "FROM orders\n" +
//                "       INNER JOIN orders_items\n" +
//                "               ON orders.order_id = orders_items.order_id\n" +
//                "       INNER JOIN items\n" +
//                "               ON orders_items.item_id = items.item_id\n" +
//                "WHERE orders.user_id = 1\n" +
//                "ORDER BY orders.order_id\n" +
//                "\n";
//
//        String getUserQuery = "SELECT * FROM users WHERE users.user_id = 1";
//        return null;
//    }
}
