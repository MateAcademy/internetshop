package mate.academy.internetshop.controller.order;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
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
        Bucket bucket = bucketService.getByUserId(userId);

//        if (bucket == null) {
//            List<Order> listOrdersWhenBucketNull = orderService.getAll();
//            if (listOrdersWhenBucketNull.size() == 0) {
//                req.getRequestDispatcher("/WEB-INF/views/orderEmpty.jsp").forward(req, resp);
//            } else {
//                req.setAttribute("orders", listOrdersWhenBucketNull);
//                req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
//            }
//        } else if (bucket != null) {
            List<Item> listItemFromBucket = bucket.getItems();
        if (listItemFromBucket.size() != 0) {
            Order order = new Order(userId, listItemFromBucket);
            bucketService.delete(userId);
            orderService.update(order);
        }

            List<Order> orderList = orderService.getAll();

        req.setAttribute("orders", orderList);
        req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);












//
//            if (orderList.size() == 0) {
//                orderService.create(order);
//                bucketService.delete(userId);
//                List<Order> orderList2 = orderService.getAll();
//
//
//
//            } else if (orderList.size() != 0) {
//                orderService.update(order);
//                bucketService.delete(userId);
//                List<Order> orderList2 = orderService.getAll();
//                req.setAttribute("orders", orderList2);
//                req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
            }
        }
//    }
//}
