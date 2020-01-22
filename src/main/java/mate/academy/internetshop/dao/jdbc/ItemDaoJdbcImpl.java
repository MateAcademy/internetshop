package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.util.DbConnector;

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

    public static void main(String[] args) {
        ItemDaoJdbcImpl itemDaoJdbc = new ItemDaoJdbcImpl();
        Item item = new Item(15L, "яблоко", 55., "США");
        itemDaoJdbc.update(item);
    }

    @Override
    public Item update(Item item) {
        try (Connection connection = DbConnector.connect()) {

            String sql = String.format("UPDATE shop.items SET name='%s', price=" + item.getPrice() + ", description='%s' WHERE item_id=%d",
                    item.getName(), item.getDescription(), item.getId());
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean delete(Item item) {
        try (Connection connection = DbConnector.connect()) {
            Statement statement = connection.createStatement();
            String name = item.getName();
            String sql = "DELETE FROM shop.items WHERE item_id =" + item.getId();
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Item create(Item item) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("INSERT INTO shop.items (name, price, description)"
                    + " VALUES ('%s', '%s', '%s')", item.getName(), item.getPrice(), item.getDescription());
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        try (Connection connection = DbConnector.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.items WHERE item_id = " + id + "");
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

        return Optional.empty();
    }

    @Override
    public List<Item> getAll() {
        List<Item> itemList = new ArrayList<>();
        try (Connection connection = DbConnector.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.items");
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
        return itemList;
    }

    @Override
    public boolean deleteById(Long itemId) {
        try (Connection connection = DbConnector.connect()) {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM shop.items WHERE item_id = " + itemId;
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
