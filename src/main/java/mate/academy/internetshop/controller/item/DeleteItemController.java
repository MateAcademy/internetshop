package mate.academy.internetshop.controller.item;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

/**
 * @author Sergey Klunniy
 */
public class DeleteItemController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteItemController.class);

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");

        String itemId =  req.getParameter("item_id");
        Long itemIdL = Long.valueOf(itemId);
        logger.info("delete item in bd " + itemService.get(itemIdL).getName());
        itemService.delete(itemIdL);

        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
