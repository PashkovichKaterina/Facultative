package by.trjava.pashkovich.facultative.controller.command.exception;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException() {
        super();
    }

    public NoSuchCommandException(String message) {
        super(message);
    }

    public NoSuchCommandException(Throwable e) {
        super(e);
    }

    public NoSuchCommandException(String message, Throwable e) {
        super(message, e);
    }
}
