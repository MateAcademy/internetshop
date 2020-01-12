package mate.academy.internetshop.controller.bucket;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class DeleteItemsInBucketsController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s =  req.getParameter("item_id");
        Long id = Long.valueOf(s);
        Bucket bucket = bucketService.get(1L);
        List<Item> items = bucket.getItems();
        Item item = itemService.get(id);
        items.remove(item);
        bucketService.update(bucket);

        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/showBucket.jsp").forward(req, resp);
    }
}