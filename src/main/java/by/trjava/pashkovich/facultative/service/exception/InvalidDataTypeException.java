package by.trjava.pashkovich.facultative.service.exception;

public class InvalidDataTypeException extends ServiceException {
    public InvalidDataTypeException() {
        super();
    }

    public InvalidDataTypeException(String message) {
        super(message);
    }

    public InvalidDataTypeException(Throwable e) {
        super(e);
    }

    public InvalidDataTypeException(String message, Throwable e) {
        super(message, e);
    }
}
