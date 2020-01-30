package mate.academy.internetshop.controller;

import mate.academy.internetshop.dao.BasketDao;
import mate.academy.internetshop.dao.jdbc.BasketDaoJdbcImpl;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;
import org.apache.log4j.Logger;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 * @author Sergey Klunniy
 */
public class LoginController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Inject
    private static UserService userService;

    @Inject
    private static BasketDao basketDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("psw");
        try {

            byte [] salt = userService.getSalt(login);

            System.out.println(new String(salt));
            if (HashUtil.hashPassword(password, salt).equals(userService.getPassword(login))) {

                User user = userService.login(login, HashUtil.hashPassword(password, salt));
                Set<Role> userRole = userService.getUserRole(user);
                user.setRoles(userRole);

                Optional<Basket> optBasket = basketDao.getByUserId(user.getId());
                if (!optBasket.isPresent()) {
                    Basket basket = new Basket(user.getId());
                    basketDao.create(basket);
                }

                HttpSession session = req.getSession(true);
                session.setAttribute("userId", user.getId());

                Cookie cookie = new Cookie("MATE", user.getToken());
                logger.info("user login " + user.getLogin());
                resp.addCookie(cookie);
                resp.sendRedirect(req.getContextPath() + "/servlet/mainController");
            } else {
                throw new AuthenticationException();
            }




        } catch (AuthenticationException e) {
            logger.warn("user didn't authentication + " + e);
            req.setAttribute("errorMsg", "Incorrect login or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
