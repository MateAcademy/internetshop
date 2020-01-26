package mate.academy.internetshop.controller.order;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class CreateOrderController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Basket basket = bucketService.getByUserId(userId);

        List<Item> listItemFromBucket = basket.getItems();
        if (listItemFromBucket.size() != 0) {
            Order order = new Order(userId, listItemFromBucket);
            bucketService.delete(userId);
            orderService.update(order);
        }
        List<Order> orderList = orderService.getAll();
        req.setAttribute("orders", orderList);
        req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
    }
}
