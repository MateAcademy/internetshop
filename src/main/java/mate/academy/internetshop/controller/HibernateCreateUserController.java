package mate.academy.internetshop.controller;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.hibernate.UserDaoHibernateImpl;
import mate.academy.internetshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sergey Klunniy
 */
public class HibernateCreateUserController extends HttpServlet {

    private static final UserDao userDao = new UserDaoHibernateImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDao.create(new User("Slavik", "Fedorov", "ava@mail.ru",
                "888", "login", "pass", "token"));

        super.doGet(req, resp);
    }
}
