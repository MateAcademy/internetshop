package mate.academy.internetshop.controller.bucket;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;

/**
 * @author Sergey Klunniy
 */
public class ShowUserBuckets extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");

        Bucket bucket = bucketService.getByUserId(userId);
        List<Item> items = bucket.getItems();
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/showBucket.jsp").forward(req, resp);
    }
}
