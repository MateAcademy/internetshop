package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.DbConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sergey Klunniy
 */
@Dao
public class RoleDaoJdbcImpl implements RoleDao {

    private static Logger logger = Logger.getLogger(RoleDaoJdbcImpl.class);

    @Override
    public Set<Role> getUserRole(User user) {
        String sql = "SELECT role_name FROM shop.users INNER JOIN shop.users_roles ON users.user_id = users_roles.user_id" +
                " INNER JOIN shop.roles ON users_roles.role_id = roles.role_id WHERE users.user_id = ?";
        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, user.getId());
            ResultSet resultSet = stmt.executeQuery();
            Set<Role> roles = new HashSet<>();
            while (resultSet.next()) {
                String role = resultSet.getString("role_name");
                Role role1 = Role.of(role);
                roles.add(role1);
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean setUserRole(User user) {
        String sql = "INSERT INTO shop.users_roles (user_id, role_id) VALUE (?, ?);";
        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setString(1, String.valueOf(user.getId()));
            stmt.setString(2, String.valueOf(2));
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setAdminRole(User user) {
        String sql = "INSERT INTO shop.users_roles (user_id, role_id) VALUE (?, ?);";
        try (Connection connection = DbConnector.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setString(1, String.valueOf(user.getId()));
            stmt.setString(2, String.valueOf(1));
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
