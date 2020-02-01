package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.AuthenticationException;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */
public interface UserService {
    String getPassword(String login);

    byte[] getSalt(String login);

    User create(User user);

    User get(Long idUser);

    User update(User user);

    boolean delete(Long userId);

    boolean delete(User user);

    List<User> getAll();

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    Set<Role> getUserRole(User user);

    Set<String> getUserRoleName(User user);
}
