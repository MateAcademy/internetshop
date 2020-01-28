package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.controller.exceptions.DataProcessingException;
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
            throw new DataProcessingException("Can't set 'user' role ", e);
        }
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
            throw new DataProcessingException("Can't set 'admin' role ", e);
        }
    }
}
