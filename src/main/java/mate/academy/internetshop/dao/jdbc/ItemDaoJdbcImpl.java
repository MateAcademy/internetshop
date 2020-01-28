package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.util.DbConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
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
        try (Connection connection = DbConnector.connect();
             Statement statement = connection.createStatement()
        ) {
            String sql = String.format(java.util.Locale.ROOT, "UPDATE shop.items SET name='%s', " +
                            "price=%.2f, description='%s' WHERE item_id=%d", item.getName(), item.getPrice(),
                    item.getDescription(), item.getId());
            statement.executeUpdate(sql);
            logger.info("update item in bd susses : " + item.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean delete(Item item) {
        try (Connection connection = DbConnector.connect();
             Statement statement = connection.createStatement()
        ) {
            String sql = String.format("DELETE FROM shop.items WHERE item_id =%d", item.getId());
            statement.executeUpdate(sql);
            logger.info("delete item in bd susses : " + item.getName());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Item create(Item item) {
        try (Connection connection = DbConnector.connect();
             Statement statement = connection.createStatement()
        ) {
            String sql = String.format("INSERT INTO shop.items (name, price, description)"
                    + " VALUES ('%s', '%s', '%s')", item.getName(), item.getPrice(), item.getDescription());
            statement.executeUpdate(sql);
            logger.info("create item in bd susses : " + item.getName());
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        try (Connection connection = DbConnector.connect();
             Statement statement = connection.createStatement()
        ) {
            String sql = String.format("SELECT * FROM shop.items WHERE item_id =%d ", id);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item itemFromDB = new Item(
                        resultSet.getLong("item_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"));
                return Optional.of(itemFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("get Optional<item> in bd susses, id= " + id);
        return Optional.empty();
    }

    @Override
    public List<Item> getAll() {
        List<Item> itemList = new ArrayList<>();
        try (Connection connection = DbConnector.connect();
             Statement statement = connection.createStatement()
        ) {
            String sql = "SELECT * FROM shop.items";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item itemFromDB = new Item(
                        resultSet.getLong("item_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"));
                itemList.add(itemFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("getAll List<Item> in bd susses");
        return itemList;
    }

    @Override
    public boolean deleteById(Long itemId) {
        try (Connection connection = DbConnector.connect();
             Statement statement = connection.createStatement()
        ) {
            String sql = String.format("DELETE FROM shop.items WHERE item_id =%d ", itemId);
            statement.executeUpdate(sql);
            logger.info("deleteById item in bd susses, id= " + itemId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
