//package mate.academy.internetshop.dao.jdbc;
//
//import mate.academy.internetshop.dao.OrderDao;
//import mate.academy.internetshop.db.Storage;
//import mate.academy.internetshop.lib.Dao;
//import mate.academy.internetshop.model.Item;
//import mate.academy.internetshop.model.Order;
//import mate.academy.internetshop.model.User;
//import mate.academy.internetshop.util.DbConnector;
//import org.apache.log4j.Logger;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author Sergey Klunniy
// */
//@Dao
//public class OrderDaoJdbcImpl implements OrderDao {
//
//    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);
//    private static String ADD_ORDER_IN_DB = "INSERT INTO order (first name, last name, number_phone,)" +
//            ...entered_code,basket_id)
//
//    VALUES('%s','%s','%s','%s','5s',5d)" ;
//    private static String GET_ALL_ORDERS = "SELECT * FROM 'order' INNER JOIN basket b on 'order.basket_id = " +
//            "INNER JOIN product_basket pb ON b.id = pb.basket_id\n '" +
//            INNER
//    JOIN products
//    on pb.pro
//
//    @Override
//
//    public Order create(Order order) {
//        // INSERT INTO 'shop'.'orders'('user_id') VALUES('1')
//        // INSERT INTO 'shop'.'orders_items' ('order_id', 'item_id') VALUES ('1', '1');
//        // INSERT INTO 'shop'.'orders_items' ('order_id', 'item_id') VALUES ('1', '2');
//
//        Statement stmt = null;
//        String query = "INSERT INTO 'orders' ('user_id') VALUES ('" + order.getUser().getId() + "');";
//
//        нам нужно получить лонг ордер id
//        Long orderId = stat.executeUpdate(query);
//
//
////        order.getItems() нам нужно с ордера гет айтем сахранить всех айтемы
//
//        String insertOrderItemQuery = "INSERT INTO 'shop'.'orders_items' ('order_id', 'item_id') " +
//                "VALUES ('%s', '%s');";
//
//        for (Item item : order.getItems()) {
//            try {
//                stmt.execute(String.format(insertOrderItemQuery, orderId, item.getId()));
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return new Order(orderId, order.getUser(), order.getItems());
//    }
//
//
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
//}
