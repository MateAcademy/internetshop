package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.controller.exceptions.AuthenticationException;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.UserDaoImpl;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

import mate.academy.internetshop.util.DbConnector;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * @author Sergey Klunniy
 */
@Dao
public class UserDaoJdbcImpl implements UserDao {

    public static void main(String[] args) {
        UserDaoJdbcImpl userDao = new UserDaoJdbcImpl();
        userDao.create(new User("Oleg", "Fedorov", "sf@gmail.com", "+380501430700",
                "ava", "1", "TTT-ttt"));
    }

    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    @Override
    public User create(User user) {

        String sql = String.format("INSERT INTO shop.users (name, surname, email, phone, " +
                "login, password, token) VALUES (?, ?, ?, ?, ?, ?, ?)");

        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
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
    public Optional<User> get(Long id) {
        String sql = "SELECT * FROM shop.users INNER JOIN shop.users_roles ON users.user_id = users_roles.user_id" +
                "INNER JOIN shop.roles ON users_roles.role_id = roles.role_id WHERE user_id = ?";
        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

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
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long entityId) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
//        try (Connection connection = DbConnector.connect()) {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.users");
//            while (resultSet.next()) {
//                User userFromDb = new User(
//                        resultSet.getLong("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("surname"),
//                        resultSet.getString("email"),
//                        resultSet.getString("phone"),
//                        resultSet.getString("login"),
//                        resultSet.getString("password"),
//                        resultSet.getString("token"),
//                        resultSet.getString("roles"));
//                userList.add(userFromDb);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return userList;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User login(String login, String password) {
        String query = "SELECT * FROM  users WHERE login = ? and password = ?;";

        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                long userId = rs.getLong("user_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String token = rs.getString("token");
                User user = new User(userId);
                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setToken(token);
                return user;
            }
        } catch (SQLException e) {
            logger.error("Can't get user by login =" + login, e);
        }
//        throw new AuthenticationException("Can't get user by login =" + login);
        return null;
    }

    @Override
    public Optional<User> getByToken(String token) {
        String qery = "SELECT  FROM " + "users WHERE token = \'" + token + "\';";

//        try (Statement stmt = connection.createStatement()) {
//            ResultSet rs = stmt.executeQuery(qery);
//            while (rs.next()) {
//                long userId = rs.getLong("user_id");
//                String name = rs.getString("name");
//            }
//
//        }

        return Optional.of(new User());
    }
}
