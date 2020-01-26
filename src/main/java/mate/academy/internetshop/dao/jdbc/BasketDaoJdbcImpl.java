//package mate.academy.internetshop.dao.jdbc;
//
//import com.mysql.cj.log.Log;
//import mate.academy.internetshop.dao.BasketDao;
//import mate.academy.internetshop.dao.impl.BasketDaoImpl;
//import mate.academy.internetshop.lib.Dao;
//import mate.academy.internetshop.model.Item;
//import mate.academy.internetshop.model.User;
//import mate.academy.internetshop.util.DbConnector;
//import org.apache.log4j.Logger;
//
//import java.awt.*;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
///**
// * @author Sergey Klunniy
// */
//@Dao
//public class BasketDaoJdbcImpl implements BasketDao {
//
//    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);
//    private static final String CREATE_BASKET_IN_DB = "INSERT INTO basket (user_id) VALLUES"
//    private static final String ADD_PRODUCT_IN_BASKET = "INSERT INTO product_basket (user_id) VALLUES"
//       "VALUES (%d, %d)";
//    private static final String GET_PRODUCTS_BY_USER_FROM_DB = "SELECT products.id"
//    "FROM products INNER JOIN basket WHERE basket.id = 1";
//
//    public void createBasket(User user) {
//        try (Connection connection = DbConnector.connect()) {
//            String sql = String.format(CREATE_BASKET_IN_DB, user.getId());
//            Statement stetment = connection.createStatement();
//            stetment.execute(sql);
//            logger.info(user + " creates a basket.");
//        } catch (SQLException e) {
//            logger.warn(user + " can't create a basket.");
//        }
//    }
//
//    @Override
//    public void addProductInBasket(Long id, Item item) {
//        try (Connection connection = DbConnector.connect()) {
//            String sql = String.format(ADD_PRODUCT_IN_BASKET, item.getId(), id);
//            Statement stetment = connection.createStatement();
//             stetment.execute(sql);
//            logger.info(item + " added in basket.");
//        } catch (SQLException e) {
//            logger.warn(item + " can't be added in basket.");
//        }
//    }
//
//    @Override
//    public List<Item> getItems(User user) {
//        List<Item> items = new ArrayList<>();
//        try (Connection connection = DbConnector.connect()) {
//            String sql = String.format(GET_PRODUCTS_BY_USER_FROM_DB, user.getId());
//            Statement stetment = connection.createStatement();
//            ResultSet resultSet = stetment.executeQuery(sql);
//            if (resultSet.next()) {
//                Item itemFromDb = new Item(
//                        resultSet.getLong("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("description"),
//                        resultSet.getFloat("price"));
//                ((ArrayList) items).add(itemFromDb);
//                return items;
//            }
//        } catch (SQLException e) {
//            logger.warn("User's basket " + user + "} is not found.");
//        }
//        return null;
//    }
//
//    @Override
//    public int getCountProducts(User user) {
//        List<Item> items = getItems(user);
//        return items.size();
//    }
//
//    @Override
//    public Basket getBasket(User user) {return null;}
//}
