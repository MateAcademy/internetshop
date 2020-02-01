package mate.academy.internetshop.service.idgenerators;

/**
 * @author Sergey Klunniy
 */
public class BucketIdGenerator {

    private static Long id = 1L;

    public static long getGeneratedId() {
        return id++;
    }
}
