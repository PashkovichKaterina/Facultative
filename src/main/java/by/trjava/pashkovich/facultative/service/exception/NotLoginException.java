package by.trjava.pashkovich.facultative.service.exception;

public class NotLoginException extends ServiceException {
    public NotLoginException() {
        super();
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(Throwable e) {
        super(e);
    }

    public NotLoginException(String message, Throwable e) {
        super(message, e);
    }
}
