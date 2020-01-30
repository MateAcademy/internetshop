package mate.academy.internetshop.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;
import org.apache.log4j.Logger;

/**
 * @author Sergey Klunniy
 */
public class RegistrationUserController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegistrationUserController.class);

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registerUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
            newUser.setPassword(req.getParameter("psw"));
            userService.create(newUser);
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", newUser.getId());

            Cookie cookie = new Cookie("MATE", newUser.getToken());
            resp.addCookie(cookie);
            logger.info("registration new user");
            resp.sendRedirect(req.getContextPath() + "/servlet/mainController");
        } else {
            logger.warn("registration error new user!");
            req.setAttribute("error", "Your password not equals, enter new password:");
            req.getRequestDispatcher("/WEB-INF/views/registerUser.jsp").forward(req, resp);
        }
    }
}
