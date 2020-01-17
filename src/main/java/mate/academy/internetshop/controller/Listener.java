package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

/**
 * @author Sergey Klunniy
 */
public class Listener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(ServletContextListener.class);

    @Inject
    private static UserService userService;

    @Inject
    private static ItemService itemService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Injector.injectDependency();
            initializeDb();
            logger.info("contextInitialized run");
        } catch (IllegalAccessException e) {
            logger.error("contextInitialized  error " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void initializeDb() {
        User user = new User("Slavik", "Fedorov", "fed.urist@dn.ua",
                "+3050888888", "ava","1");
        User user2 = new User("Sergei", "Klunniy", "ava_inet@mail.ru",
                "+30501430700", "a","1");
        userService.create(user);
        userService.create(user2);
        logger.info("user and user2 was added in db");

        Item item = new Item("apple", 35.0,"from Ukraine");
        Item item2 = new Item("grapes", 40.0,"from Russia");
        Item item3 = new Item("bread", 15.0,"from USA");
        itemService.create(item);
        itemService.create(item2);
        itemService.create(item3);
        logger.info("item, item2 and item3 was added in db");
    }
}
