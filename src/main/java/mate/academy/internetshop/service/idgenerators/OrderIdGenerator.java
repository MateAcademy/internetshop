package mate.academy.internetshop.service.idgenerators;

/**
 * @author Sergey Klunniy
 */
public class OrderIdGenerator {
    private static long id = 0;

    public static long getId() {
        return id++;
    }
}
