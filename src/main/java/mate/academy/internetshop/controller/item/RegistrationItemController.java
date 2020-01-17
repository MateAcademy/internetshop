package mate.academy.internetshop.controller.item;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

/**
 * @author Sergey Klunniy
 */
public class RegistrationItemController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegistrationItemController.class);

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Item item = new Item();
        String name = req.getParameter("item_name");
        item.setName(name);
        Double price = Double.valueOf(req.getParameter("item_price"));
        item.setPrice(price);
        String description = req.getParameter("item_description");
        item.setDescription(description);

        itemService.create(item);
        logger.info("Add to bd new item " + item.getName());

        req.setAttribute("name", name);
        req.setAttribute("price", price);
        req.setAttribute("description", description);
        req.getRequestDispatcher("/WEB-INF/views/showNewItem.jsp").forward(req, resp);
    }
}
