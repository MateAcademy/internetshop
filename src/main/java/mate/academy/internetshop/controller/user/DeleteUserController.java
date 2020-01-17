package mate.academy.internetshop.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

/**
 * @author Sergey Klunniy
 */
public class DeleteUserController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteUserController.class);

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userId = req.getParameter("user_id");
        userService.delete(Long.valueOf(userId));
        logger.info("delete user in bd");
        resp.sendRedirect(req.getContextPath() + "/servlet/show-all-users");
    }
}
