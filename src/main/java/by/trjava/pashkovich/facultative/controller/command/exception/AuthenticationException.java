package by.trjava.pashkovich.facultative.controller.command.exception;

public class AuthenticationException extends Exception {
    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable e) {
        super(e);
    }

    public AuthenticationException(String message, Throwable e) {
        super(message, e);
    }
}
