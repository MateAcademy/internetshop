package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;

/**
 * @author Sergey Klunniy
 */
public class Listener implements ServletContextListener {

    @Inject
    private static UserService userService;

    @Inject
    private static ItemService itemService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            System.out.println("Inject in Listener");
            Injector.injectDependency();
            User user = new User("Slavik", "Fedorov", "fed.urist@dn.ua",
                    "+3050888888", "a","1");
            User user2 = new User("Sergei", "Klunniy", "ava_inet@mail.ru",
                    "+30501430700", "ava","1");
            userService.create(user);
            userService.create(user2);

            Item item = new Item("apple", 35.0,"from Ukraine");
            Item item2 = new Item("grapes", 40.0,"from Russia");
            Item item3 = new Item("bread", 15.0,"from USA");
            itemService.create(item);
            itemService.create(item2);
            itemService.create(item3);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
