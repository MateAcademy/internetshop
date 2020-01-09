package mate.academy.internetshop.service;

import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */
public interface UserService {
    User create(User user);

    User get(Long idUser);

    User update(User user);

    boolean delete(Long userId);

    boolean delete(User user);
}