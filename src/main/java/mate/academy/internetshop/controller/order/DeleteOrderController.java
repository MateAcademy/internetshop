package mate.academy.internetshop.controller.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.OrderService;
import org.apache.log4j.Logger;

/**
 * @author Sergey Klunniy
 */
public class DeleteOrderController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteOrderController.class);

    @Inject
    private static OrderService orderService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");

        String orderId =  req.getParameter("order_id");
        orderService.delete(Long.valueOf(orderId));
        logger.info("delete order in bd ");
        resp.sendRedirect(req.getContextPath() + "/servlet/orders");
    }
}
