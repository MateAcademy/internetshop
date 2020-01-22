package mate.academy.internetshop.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.DbConnector;

import javax.naming.AuthenticationException;

/**
 * @author Sergey Klunniy
 */
@Dao
public class UserDaoImpl implements UserDao {

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
//        userDao.create(new User("Sergey", "Fedorov", "sf@gmail.com", "+380501430700", "ava",
//                "1"));

        System.out.println(userDao.getAll());
    }
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


//    @Override
//    public User create(User user) {
//        Storage.users.add(user);
//        return user;
//    }

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

//    @Override
//    public List<User> getAll() {
//        return Storage.users;
//    }

    @Override
    public Optional<User> get(Long idUser) {
        return Optional.ofNullable(Storage.users
                .stream()
                .filter(b -> b.getId().equals(idUser))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find user with id: "
                        + idUser)));
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (user.getId().equals(Storage.users.get(i).getId())) {
                Storage.users.remove(i);
            }
        }
        Storage.users.add(user);
        return user;
    }

    @Override
    public boolean deleteById(Long entityId) {
        Optional<User> optUser = get(entityId);
        if (optUser.isPresent()) {
            User user = optUser.get();
            if (Storage.users.remove(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Storage.users.stream()
                .filter(o -> o.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public User login(String login, String password)
            throws AuthenticationException {
        Optional<User> user = Storage.users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("incorrect username or password");
        }
        return user.get();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Storage.users.stream()
                .filter(u -> u.getToken().equals(token))
                .findFirst();
    }
}
