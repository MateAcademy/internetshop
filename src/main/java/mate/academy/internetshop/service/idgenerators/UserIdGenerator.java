package mate.academy.internetshop.service.idgenerators;

/**
 * @author Sergey Klunniy
 */
public class UserIdGenerator {
    private static long id = 1;

    public static long getId() {
        return id++;
    }
}
