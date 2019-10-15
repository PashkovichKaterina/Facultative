package by.trjava.pashkovich.facultative.service.exception;

public class RequiredAccountInformationNotEnteredException extends ServiceException {
    public RequiredAccountInformationNotEnteredException() {
        super();
    }

    public RequiredAccountInformationNotEnteredException(String message) {
        super(message);
    }

    public RequiredAccountInformationNotEnteredException(Throwable e) {
        super(e);
    }

    public RequiredAccountInformationNotEnteredException(String message, Throwable e) {
        super(message, e);
    }
}
