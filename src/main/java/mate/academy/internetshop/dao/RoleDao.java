package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */
public interface RoleDao {
    boolean setAdminRole(User user);

    boolean setUserRole(User user);
}
