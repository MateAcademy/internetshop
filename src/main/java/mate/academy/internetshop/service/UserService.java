package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.User;

import javax.naming.AuthenticationException;

/**
 * @author Sergey Klunniy
 */
public interface UserService {
    User create(User user);

    User get(Long idUser);

    User update(User user);

    boolean delete(Long userId);

    boolean delete(User user);

    List<User> getAll();

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
