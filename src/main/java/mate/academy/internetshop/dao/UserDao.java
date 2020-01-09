package mate.academy.internetshop.dao;

import java.util.Optional;

import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */
public interface UserDao {
    User create(User user);

    Optional<User> get(Long idUser);

    User update(User user);

    boolean delete(Long userId);

    boolean delete(User user);
}
