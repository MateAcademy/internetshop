package mate.academy.internetshop.controller.user;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sergey Klunniy
 */
public class RegistrationUserController extends HttpServlet {

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registerUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String password = req.getParameter("psw");
        String repeatPassword = req.getParameter("psw2");
        if (password.equals(repeatPassword)) {
            User newUser = new User();
            newUser.setName(req.getParameter("user_name"));
            newUser.setSurname(req.getParameter("user_surname"));
            newUser.setEmail(req.getParameter("email"));
            newUser.setPhone(req.getParameter("phone"));
            newUser.setLogin(req.getParameter("login"));
            newUser.setPassword(password);
            userService.create(newUser);
            resp.sendRedirect(req.getContextPath() + "/servlet/show-all-users");
        } else {
            req.setAttribute("error", "Your password not equals, enter new password:");
            req.getRequestDispatcher("/WEB-INF/views/registerUser.jsp").forward(req, resp);
        }
    }
}
