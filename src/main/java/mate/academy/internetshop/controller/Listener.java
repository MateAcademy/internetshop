package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
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
            logger.info("contextInitialized run");
        } catch (IllegalAccessException e) {
            logger.error("contextInitialized  error " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
