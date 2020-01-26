package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.controller.exceptions.AuthenticationException;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.UserDaoImpl;
import mate.academy.internetshop.lib.Dao;
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
public class UserDaoJdbcImpl implements UserDao {

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
//        userDao.create(new User("Sergey", "Fedorov", "sf@gmail.com", "+380501430700", "ava",
//                "1"));
        System.out.println(userDao.getAll());
    }

    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    @Override
    public User create(User user) {
        try (Connection connection = DbConnector.connect()) {
            String sql = String.format("INSERT INTO shop.users (name, surname, email, phone, " +
                            "login, password, token)" + " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", user.getName(),
                    user.getSurname(), user.getEmail(), user.getPhone(), user.getLogin(), user.getPassword(),
                    user.getToken());
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<User> get(Long id) {
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
        try (Connection connection = DbConnector.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM shop.users");
            while (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("token"),
                        resultSet.getString("roles"));
                userList.add(userFromDb);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
