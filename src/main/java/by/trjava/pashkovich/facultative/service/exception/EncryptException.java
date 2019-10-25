package by.trjava.pashkovich.facultative.service.exception;

public class EncryptException extends ServiceException {
    public EncryptException() {
        super();
    }

    public EncryptException(String message) {
        super(message);
    }

    public EncryptException(Throwable e) {
        super(e);
    }

    public EncryptException(String message, Throwable e) {
        super(message, e);
    }
}
