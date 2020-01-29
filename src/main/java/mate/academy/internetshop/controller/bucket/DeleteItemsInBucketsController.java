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
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

/**
 * @author Sergey Klunniy
 */
public class DeleteItemsInBucketsController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteItemsInBucketsController.class);

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Basket basket = bucketService.getByUserId(userId);

        String stringItemId =  req.getParameter("item_id");
        Long id = Long.valueOf(stringItemId);
        List<Item> items = basket.getItems();
        Item item = itemService.get(id);
        items.remove(item);
        logger.info("delete item in basket " + item.getName());
        bucketService.update(basket);

        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/showBucket.jsp").forward(req, resp);
    }
}
