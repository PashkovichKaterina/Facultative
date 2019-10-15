package by.trjava.pashkovich.facultative.service.exception;


public class EmptyDataException extends ServiceException {
    public EmptyDataException() {
        super();
    }

    public EmptyDataException(String message) {
        super(message);
    }

    public EmptyDataException(Throwable e) {
        super(e);
    }

    public EmptyDataException(String message, Throwable e) {
        super(message, e);
    }
}
