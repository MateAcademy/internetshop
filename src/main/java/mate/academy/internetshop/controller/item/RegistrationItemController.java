package mate.academy.internetshop.controller.item;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sergey Klunniy
 */
public class RegistrationItemController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Item item = new Item();
        String name =req.getParameter("item_name");
        item.setName(name);
        Double price = Double.valueOf(req.getParameter("item_price")); item.setPrice(price);
        String description = req.getParameter("item_description"); item.setDescription(description);

        itemService.create(item);

        req.setAttribute("name", name);
        req.setAttribute("price", price);
        req.setAttribute("description", description);
        req.getRequestDispatcher("/WEB-INF/views/showNewItem.jsp").forward(req, resp);
    }
}
