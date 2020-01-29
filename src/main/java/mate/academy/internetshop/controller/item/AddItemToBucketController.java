package mate.academy.internetshop.controller.item;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

/**
 * @author Sergey Klunniy
 */
public class AddItemToBucketController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddItemToBucketController.class);

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Long idItem = Long.valueOf(req.getParameter("item_id"));
        Item item = itemService.get(idItem);

        Basket basket = bucketService.getByUserId(userId);
        bucketService.addItem(basket, item);
        logger.info("of item to basket " + item.getName() );

        resp.sendRedirect(req.getContextPath() + "/servlet/showAllItems");
    }
}
