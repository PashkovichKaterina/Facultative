package by.trjava.pashkovich.facultative.service.exception;

public class NoSuchCourseException extends ServiceException {
    public NoSuchCourseException() {
        super();
    }

    public NoSuchCourseException(String message) {
        super(message);
    }

    public NoSuchCourseException(Throwable e) {
        super(e);
    }

    public NoSuchCourseException(String message, Throwable e) {
        super(message, e);
    }
}
