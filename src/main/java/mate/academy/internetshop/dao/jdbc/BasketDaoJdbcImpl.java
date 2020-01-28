package mate.academy.internetshop.dao.jdbc;

import com.mysql.cj.log.Log;
import mate.academy.internetshop.dao.BasketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.BasketDaoImpl;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.DbConnector;
import org.apache.log4j.Logger;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
@Dao
public class BasketDaoJdbcImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);

    @Inject
    private static ItemDao itemDao;

    @Inject
    private static UserDao userDao;

    @Override
    public Optional<Basket> getByUserId(Long userId) {
        String sql = "SELECT * FROM shop.baskets INNER JOIN shop.items_baskets ON baskets.basket_id = items_baskets.basket_id WHERE user_id = ?;";
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            Long basketId = null, userIdfromBd = null, itemId;  List<Item> items = new ArrayList<>();
            while (rs.next()) {
                basketId = rs.getLong("basket_id");
                userIdfromBd = rs.getLong("user_id");
                itemId = rs.getLong("item_id");
                Item item = itemDao.get(itemId).get();
                items.add(item);
            }
            Basket basket = new Basket(basketId, userIdfromBd, items);
            return Optional.of(basket);
        } catch (SQLException e) {
            logger.warn("Can't get all orders from db");
        }
        return null;
    }


    @Override
    public Basket create(Basket basket) {
        String sql = "INSERT INTO shop.baskets (user_id) VALUES (?);";

        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, basket.getUserId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                Long basketId = rs.getLong(1);
                basket.setId(basketId);
            }
            addItems(basket, basket.getItems());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return basket;
    }

    private void addItems(Basket basket, List<Item> items) {
        String sql = "INSERT INTO shop.items_baskets(basket_id, item_id) VALUES (?, ?);";
        changeBasketItems(basket, items, sql);
    }

    private void changeBasketItems(Basket basket, List<Item> items, String sql) {
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Item item : items) {
                statement.setLong(1, basket.getId());
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Optional<Basket> get(Long id) {
        String sql = "SELECT * FROM shop.baskets INNER JOIN shop.items_baskets ON baskets.basket_id = items_baskets.basket_id " +
                "WHERE baskets.basket_id = ?;";
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            Long basketId = null, userId = null;
            List<Long> items = new ArrayList<>();

            while (rs.next()) {
                basketId = rs.getLong("basket_id");
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
            Basket basket = new Basket(basketId, user.getId(), itemsFromBd);
            return Optional.of(basket);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Basket update(Basket basket) {
        String query = "UPDATE shop.baskets SET user_id = ? WHERE basket_id = ?;";
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, basket.getUserId());
            statement.setLong(2, basket.getId());
            statement.executeUpdate();
            deleteAllItems(basket);
            addItems(basket, basket.getItems());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return basket;
    }

    private void deleteAllItems(Basket basket) {
        String query = "DELETE FROM shop.items_baskets WHERE basket_id = ?;";
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, basket.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteById(Long basketId) {
        Optional<Basket> optBasket = get(basketId);
        if (optBasket.isPresent()) {
            return delete(optBasket.get());
        }
        return false;
    }

    @Override
    public boolean delete(Basket basket) {
        deleteItems(basket, basket.getItems());
        String query = "DELETE FROM shop.baskets WHERE basket_id = ?;";
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, basket.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private void deleteItems(Basket basket, List<Item> items) {
        String query = "DELETE FROM shop.items_baskets WHERE basket_id = ? AND item_id = ?;";
        changeBasketItems(basket, items, query);
    }





    @Override
    public List<Basket> getAll() {
        List<Basket> baskets = new ArrayList<>();
        String sql = "SELECT * FROM shop.baskets INNER JOIN shop.items_baskets ON baskets.basket_id = items_baskets.basket_id;";
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long basketId = rs.getLong("basket_id");
                Long userId = rs.getLong("user_id");
                Long itemId = rs.getLong("item_id");
                List<Item> items = new ArrayList<>();
                Item item = itemDao.get(itemId).get();
                items.add(item);
                Basket basket = new Basket(basketId, userId, items);

                baskets.add(basket);
            }

            return baskets;
        } catch (SQLException e) {
            logger.warn("Can't get all orders from db");
        }
        return null;
    }


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
}
