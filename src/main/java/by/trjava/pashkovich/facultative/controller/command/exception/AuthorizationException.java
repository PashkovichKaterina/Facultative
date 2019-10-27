package by.trjava.pashkovich.facultative.controller.command.exception;

public class AuthorizationException extends Exception {
    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(Throwable e) {
        super(e);
    }

    public AuthorizationException(String message, Throwable e) {
        super(message, e);
    }
}
