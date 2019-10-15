package by.trjava.pashkovich.facultative.service.exception;

public class UnavailableOperationException extends ServiceException {
    public UnavailableOperationException() {
        super();
    }

    public UnavailableOperationException(String message) {
        super(message);
    }

    public UnavailableOperationException(Throwable e) {
        super(e);
    }

    public UnavailableOperationException(String message, Throwable e) {
        super(message, e);
    }
}
