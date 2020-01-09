package mate.academy.internetshop.service.idgenerators;

/**
 * @author Sergey Klunniy
 */
public class BucketIdGenerator {
    private static long id = 1;

    public static long getGeneratedId() {
        return id++;
    }
}
