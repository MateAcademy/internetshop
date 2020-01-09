package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Injector;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sergey Klunniy
 */
public class IndexController extends HttpServlet {

    static {
        System.out.println("IndexController is loader!!!");
            try {
                Injector.injectDependency();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
