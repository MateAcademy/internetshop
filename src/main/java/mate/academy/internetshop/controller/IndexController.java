package mate.academy.internetshop.controller;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.hibernate.UserHibDaoImpl;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sergey Klunniy
 */
public class IndexController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("/index.jsp");
    }
}
