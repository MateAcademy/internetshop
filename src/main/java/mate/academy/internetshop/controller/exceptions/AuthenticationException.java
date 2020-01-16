package mate.academy.internetshop.controller.exceptions;

/**
 * @author Sergey Klunniy
 */
public class AuthenticationException extends Exception{
    public AuthenticationException(String message) {
        super(message);
    }
}
