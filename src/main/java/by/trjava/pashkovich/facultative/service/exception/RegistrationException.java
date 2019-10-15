package by.trjava.pashkovich.facultative.service.exception;

public class RegistrationException extends ServiceException {
    public RegistrationException() {
        super();
    }

    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(Throwable e) {
        super(e);
    }

    public RegistrationException(String message, Throwable e) {
        super(message, e);
    }
}
