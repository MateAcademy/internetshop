package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.User;

import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByEmail(String email);
}
