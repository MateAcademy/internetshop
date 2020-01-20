package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

import mate.academy.internetshop.model.Role;

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
                "+3050888888", "user1","1");
        user.addRole(new Role(Role.RoleName.USER));

        User user2 = new User("Roma", "Bakumenko", "ava_inet@mail.ru",
                "+30501430700", "user2","2");
        user2.addRole(new Role(Role.RoleName.USER));

        User user3 = new User();
        user3.setName("Dmitriy");
        user3.setSurname("Zelentkiy");
        user3.addRole(Role.of("USER"));
        user3.setLogin("user3");
        user3.setPassword("3");

        User admin = new User();
        admin.setName("Sergiy");
        admin.setSurname("Klunniy");
        admin.addRole(Role.of("ADMIN"));
        admin.setLogin("admin");
        admin.setPassword("1");

        userService.create(user);
        userService.create(user2);
        userService.create(user3);
        userService.create(admin);
        logger.info("4 users was added in db");

        Item item = new Item("apple", 35.0,"from Ukraine");
        Item item2 = new Item("grapes", 40.0,"from Russia");
        Item item3 = new Item("bread", 15.0,"from USA");
        itemService.create(item);
        itemService.create(item2);
        itemService.create(item3);
        logger.info("item, item2 and item3 was added in db");
    }
}
