package mate.academy.internetshop.controller.bucket;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class ShowAllBuckets extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bucket bucket = bucketService.get(1L);

        if (bucket==null) {
            req.getRequestDispatcher("/WEB-INF/views/emptyBucket.jsp").forward(req, resp);
        } else {
            List<Item> items = bucket.getItems();
            req.setAttribute("items", items);
            req.getRequestDispatcher("/WEB-INF/views/showBucket.jsp").forward(req, resp);
        }
    }
}
