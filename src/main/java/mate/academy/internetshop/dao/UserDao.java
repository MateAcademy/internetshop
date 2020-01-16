package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.User;

import javax.naming.AuthenticationException;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByEmail(String email);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
