package by.trjava.pashkovich.facultative.service.exception;

public class AddCourseException extends ServiceException {
    public AddCourseException() {
        super();
    }

    public AddCourseException(String message) {
        super(message);
    }

    public AddCourseException(Throwable e) {
        super(e);
    }

    public AddCourseException(String message, Throwable e) {
        super(message, e);
    }
}
