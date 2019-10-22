package by.trjava.pashkovich.facultative.service.exception;


public class AddClassException extends ServiceException {
    public AddClassException() {
        super();
    }

    public AddClassException(String message) {
        super(message);
    }

    public AddClassException(Throwable e) {
        super(e);
    }

    public AddClassException(String message, Throwable e) {
        super(message, e);
    }
}
