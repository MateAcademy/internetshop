package mate.academy.internetshop.controller.bucket;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

/**
 * @author Sergey Klunniy
 */
public class ShowUserBuckets extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");

        Basket basket = bucketService.getByUserId(userId);
        List<Item> items = basket.getItems();
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/showBucket.jsp").forward(req, resp);
    }
}
