package mate.academy.internetshop.controller.order;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sergey Klunniy
 */
public class DeleteOrderController extends HttpServlet {

    @Inject
    private static OrderService orderService;
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s =  req.getParameter("item_id");
        orderService.delete(Long.valueOf(s));
        resp.sendRedirect(req.getContextPath() + "/servlet/orders");
    }
}
