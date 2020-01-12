package mate.academy.internetshop.controller.item;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.ItemService;

/**
 * @author Sergey Klunniy
 */
public class DeleteItemController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String s =  req.getParameter("item_id");
        itemService.delete(Long.valueOf(s));
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
