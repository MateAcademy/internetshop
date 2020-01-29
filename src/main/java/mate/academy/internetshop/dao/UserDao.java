package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

import javax.naming.AuthenticationException;
import java.util.Optional;
import java.util.Set;

/**
 * @author Sergey Klunniy
 */
public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByEmail(String email);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    Set<Role> getUserRole(User user);
}
