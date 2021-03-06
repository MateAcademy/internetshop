package mate.academy.internetshop.controller.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

/**
 * @author Sergey Klunniy
 */
public class GetAllOrdersController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        User user = userService.get(userId);

        List<Order> orderList;
        if (userService.getUserRoleName(user).contains("ADMIN")) {
            orderList = orderService.getAll();
        } else {
            orderList = orderService.getAllOrdersForUser(user);
        }

        req.setAttribute("orders", orderList);
        req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
    }
}
