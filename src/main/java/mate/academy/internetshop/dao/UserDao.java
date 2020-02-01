package mate.academy.internetshop.dao;

import java.util.Optional;
import java.util.Set;
import javax.naming.AuthenticationException;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */
public interface UserDao extends GenericDao<User, Long> {
    String getPassword(String login);

    byte[] getSalt(String login);

    Optional<User> findByEmail(String email);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    Set<Role> getUserRole(User user);

    Set<String> getUserRoleName(User user);
}
