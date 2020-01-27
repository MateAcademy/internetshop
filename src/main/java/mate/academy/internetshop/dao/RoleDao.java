package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

import java.util.Set;

/**
 * @author Sergey Klunniy
 */
public interface RoleDao {
    Set<Role> getUserRole(User user);

    boolean setAdminRole(User user);

    boolean setUserRole(User user);
}
