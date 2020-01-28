package mate.academy.internetshop.dao.jdbc;

import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class Main {

    public static void main(String[] args) {
        BasketDaoJdbcImpl basketDaoJdbc = new BasketDaoJdbcImpl();

//        User user = new User(4L);
//        List<Item> items = new ArrayList<>();
//        items.add(new Item(1L));
//        items.add(new Item(2L));
//        Basket basket = new Basket(2L ,user.getId(), items);
//
//        basketDaoJdbc.delete(basket);
        System.out.println(basketDaoJdbc.getAll());

    }

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}