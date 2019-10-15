package by.trjava.pashkovich.facultative.service.exception;


public class EditStudentDataException extends ServiceException {
    public EditStudentDataException() {
        super();
    }

    public EditStudentDataException(String message) {
        super(message);
    }

    public EditStudentDataException(Throwable e) {
        super(e);
    }

    public EditStudentDataException(String message, Throwable e) {
        super(message, e);
    }
}
