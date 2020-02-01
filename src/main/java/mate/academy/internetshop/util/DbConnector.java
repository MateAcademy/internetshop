package mate.academy.internetshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * @author Sergey Klunniy
 */
public class DbConnector {

    private static final Logger logger = Logger.getLogger(DbConnector.class);

    private static final String dbUrl = "jdbc:mysql://localhost:3306?useUnicode="
            + "true&serverTimezone=UTC&useSSL=false";
    private static final String login = "root";
    private static final String password = "Epic49$$";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, login, password);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("problem with to connect to db ", e);
        }
        return null;
    }
}
