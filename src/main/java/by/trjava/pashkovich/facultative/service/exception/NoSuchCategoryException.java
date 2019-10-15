package by.trjava.pashkovich.facultative.service.exception;

public class NoSuchCategoryException extends ServiceException {
    public NoSuchCategoryException() {
        super();
    }

    public NoSuchCategoryException(String message) {
        super(message);
    }

    public NoSuchCategoryException(Throwable e) {
        super(e);
    }

    public NoSuchCategoryException(String message, Throwable e) {
        super(message, e);
    }
}
