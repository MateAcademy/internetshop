package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.model.User;

/**
 * @author Sergey Klunniy
 */
public class Main {
    public static void main(String[] args) {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        User user = new User("T", "T", "T", "T", "T", "T", "T");
        System.out.println(userDaoJdbc.create(user));
    }
}
