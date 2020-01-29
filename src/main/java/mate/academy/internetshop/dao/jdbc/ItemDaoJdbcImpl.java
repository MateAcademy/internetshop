package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
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
public class ItemDaoJdbcImpl implements ItemDao {

    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    @Override
    public Item update(Item item) {
        String sql = String.format(java.util.Locale.ROOT, "UPDATE shop.items SET name='?, " +
                "price=?, description=? WHERE item_id=?;");
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setString(3, item.getDescription());
            statement.setLong(4, item.getId());
            statement.executeUpdate();
            logger.info("update item in bd susses : " + item.getName());
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update Item ", e);
        }
        return item;
    }

    @Override
    public boolean delete(Item item) {
        String sql = String.format("DELETE FROM shop.items WHERE item_id =?;");
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, item.getId());
            statement.executeUpdate();
            logger.info("delete item in bd susses : " + item.getName());
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete item ", e);
        }
    }

    @Override
    public Item create(Item item) {
        String sql = String.format("INSERT INTO shop.items (name, price, description)"
                + " VALUES (?, ?, ?);");
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setString(3, item.getDescription());
            statement.executeUpdate();
            logger.info("create item in bd susses : " + item.getName());
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create item ", e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        String sql = String.format("SELECT * FROM shop.items WHERE item_id =?;");
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item itemFromDB = new Item(
                        resultSet.getLong("item_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"));
                return Optional.of(itemFromDB);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get item in db by id ", e);
        }
        logger.info("get Optional<item> in bd susses, id= " + id);
        return Optional.empty();
    }

    @Override
    public List<Item> getAll() {
        String sql = "SELECT * FROM shop.items";
        List<Item> itemList = new ArrayList<>();
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item itemFromDB = new Item(
                        resultSet.getLong("item_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"));
                itemList.add(itemFromDB);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all items ", e);
        }
        logger.info("getAll List<Item> in bd susses");
        return itemList;
    }

    @Override
    public boolean deleteById(Long itemId) {
        String sql = String.format("DELETE FROM shop.items WHERE item_id =?;");
        try (Connection connection = DbConnector.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, itemId);
            statement.executeUpdate();
            logger.info("deleteById item in bd susses, id= " + itemId);
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete by id item in db ", e);
        }
    }
}
