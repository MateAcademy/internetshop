package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */
public interface UserDao extends GenericDao<User, Long> {
    User findByEmail(String email);
}
