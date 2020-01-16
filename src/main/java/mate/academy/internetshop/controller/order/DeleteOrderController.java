package mate.academy.internetshop.controller.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.OrderService;

/**
 * @author Sergey Klunniy
 */
public class DeleteOrderController extends HttpServlet {

    @Inject
    private static OrderService orderService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");

        String orderId =  req.getParameter("order_id");
        orderService.delete(Long.valueOf(orderId));
        resp.sendRedirect(req.getContextPath() + "/servlet/orders");
    }
}
