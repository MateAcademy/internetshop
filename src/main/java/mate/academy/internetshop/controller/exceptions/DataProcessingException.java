package mate.academy.internetshop.controller.exceptions;

/**
 * @author Sergey Klunniy
 */
public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
