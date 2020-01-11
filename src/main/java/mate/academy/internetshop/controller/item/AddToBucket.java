package mate.academy.internetshop.controller.item;

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

/**
 * @author Sergey Klunniy
 */
public class AddToBucket extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("item_id"));
        Item item = itemService.get(id);

        if (bucketService.get(1L) == null ) {
            Bucket bucket = new Bucket();
            bucket.addItem(item);
            bucketService.create(bucket);
            System.out.println("Корзину создали и положили товар в ее в базу ");
            resp.sendRedirect(req.getContextPath() + "/servlet/showAllItems");
        } else {
            Bucket bucket = bucketService.get(1L);
            bucket.addItem(item);
            bucketService.update(bucket);
            resp.sendRedirect(req.getContextPath() + "/servlet/showAllItems");
        }
    }
}
