package by.trjava.pashkovich.facultative.service.exception;

public class WorkTitleIsAlreadyContainedException extends ServiceException {
    public WorkTitleIsAlreadyContainedException() {
        super();
    }

    public WorkTitleIsAlreadyContainedException(String message) {
        super(message);
    }

    public WorkTitleIsAlreadyContainedException(Throwable e) {
        super(e);
    }

    public WorkTitleIsAlreadyContainedException(String message, Throwable e) {
        super(message, e);
    }
}
