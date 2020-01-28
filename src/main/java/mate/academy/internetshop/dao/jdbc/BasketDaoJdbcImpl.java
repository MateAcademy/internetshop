package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.controller.exceptions.DataProcessingException;
import mate.academy.internetshop.dao.BasketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.BasketDaoImpl;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.DbConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = "SELECT * FROM shop.baskets INNER JOIN shop.items_baskets " +
                "ON baskets.basket_id = items_baskets.basket_id WHERE user_id = ?;";
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            Long basketId = null, userIdfromBd = null, itemId;
            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                basketId = rs.getLong("basket_id");
                userIdfromBd = rs.getLong("user_id");
                itemId = rs.getLong("item_id");
                Item item = itemDao.get(itemId).get();
                items.add(item);
            }
            Basket basket = new Basket(basketId, userIdfromBd, items);
            logger.info("Get all optional baskets from db");
            return Optional.of(basket);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all items from bucket by user id", e);
        }
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
            throw new DataProcessingException("Can't create basket ", e);
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
            logger.info("add items in baskets");
        } catch (SQLException e) {
            throw new DataProcessingException("Can't changed in basket list items ", e);
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
            throw new DataProcessingException("Can't get basket by basket id ", e);
        }
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
            throw new DataProcessingException("Can't update basket ", e);
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
            throw new DataProcessingException("Can't delete all items in basket ", e);
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
            throw new DataProcessingException("Can't delete basket ", e);
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
            throw new DataProcessingException("Can't get all baskets ", e);
        }
    }
}
