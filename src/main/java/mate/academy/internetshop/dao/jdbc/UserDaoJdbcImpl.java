package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

import mate.academy.internetshop.util.DbConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Sergey Klunniy
 */
@Dao
public class UserDaoJdbcImpl implements UserDao {

    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    @Override
    public Optional<User> get(Long userId) {
        String sql = "SELECT * FROM shop.users INNER JOIN shop.users_roles ON users.user_id = users_roles.user_id" +
                " INNER JOIN shop.roles ON users_roles.role_id = roles.role_id WHERE users.user_id = ?";
        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);

            ResultSet resultSet = stmt.executeQuery();
            Set<Role> roles = new HashSet<>();

            User user = null;
            while (resultSet.next()) {
                user = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("token"));

                String role = resultSet.getString("role_name");
                Long roleId = resultSet.getLong("role_id");
                Role roleByUser = new Role(roleId, role);
                roles.add(roleByUser);
            }
            if (roles.size() != 0) {
                user.setRoles(roles);
            }
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        String sql = String.format("INSERT INTO shop.users (name, surname, email, phone, " +
                "login, password, token) VALUES (?, ?, ?, ?, ?, ?, ?)");
        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getLogin());
            stmt.setString(6, user.getPassword());
            stmt.setString(7, user.getToken());
            stmt.execute();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE shop.users SET name=?, surname=?, email=?, phone=?, " +
                "login=?, password=?, token=? WHERE user_id = ?";
        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getLogin());
            stmt.setString(6, user.getPassword());
            stmt.setString(7, user.getToken());
            stmt.setLong(8, user.getId());
            stmt.execute();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(User user) {
        return deleteById(user.getId());
    }

    @Override
    public boolean deleteById(Long userId) {
        String sql = "DELETE FROM shop.users WHERE user_id = ?";
        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM shop.users";
        List<User> userList = new ArrayList<>();
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("token"));
                userList.add(userFromDb);
            }
        } catch (SQLException e) {
            logger.error("Can't getAll() users in DB ", e);
        }
        return userList;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM shop.users WHERE email = ?";
        User userFromDb = null;
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userFromDb = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("token"));
            }
            return Optional.of(userFromDb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public User login(String login, String password) {
        String query = "SELECT * FROM  shop.users WHERE login = ? and password = ?;";
        User userFromDb = null;
        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                userFromDb = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("token"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFromDb;
    }

    @Override
    public Optional<User> getByToken(String token) {
        String sql = "SELECT * FROM shop.users WHERE token = ?";
        User userFromDb = null;
        try (Connection connection = DbConnector.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userFromDb = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
            }
            return Optional.of(userFromDb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
