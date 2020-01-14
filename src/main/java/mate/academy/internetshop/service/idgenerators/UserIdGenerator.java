package mate.academy.internetshop.service.idgenerators;

/**
 * @author Sergey Klunniy
 */
public class UserIdGenerator {
    private static Long id = 1L;

    public static long getGeneratedId() {
        return id++;
    }
}
