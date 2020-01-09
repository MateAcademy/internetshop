package mate.academy.internetshop;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;

import java.util.List;

/**
 * @author Sergey Klunniy
 */

public class Main {

    @Inject
    private static BucketDao bucketDao;

    //Створили наші сервіси
    @Inject
    private static ItemService itemService;

    @Inject
    private static UserService userService;

    static {
        try {
            System.out.println("IndexController in Main");
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //Створили товар і додали його у наш магазин
        Item item = new Item();
        item.setName("iPhone 11");
        itemService.create(item);

        //Зареєстрували користувача в нашій системі
        User user = new User("Sergei", "mate@gmail.com");
        userService.create(user);

        //Користувач почав купувати наші товари і додавати їх у корзину
        BucketService bucketService = new BucketServiceImpl();
        Bucket bucket = new Bucket(user.getId());
        bucketService.create(bucket);
        bucketService.addItem(bucket.getUserId(), item.getId());

        //Користувач вибрав все необхыдны йому товари
        //і натиснув кнопку "Оформити замовлення"
        OrderService orderService = new OrderServiceImpl();
        orderService.completeOrder(bucket);
        bucketService.delete(bucket.getUserId());

        //Користувач хоче подивитись історію своїх покупок
        List<Order> allOrdersForUser = orderService.getAllOrdersForUser(user.getId());
        System.out.println(allOrdersForUser);

    }
}
