package by.trjava.pashkovich.facultative.dao.exception;


public class CreatorException extends DAOException {
    public CreatorException() {
        super();
    }

    public CreatorException(String message) {
        super(message);
    }

    public CreatorException(Throwable e) {
        super(e);
    }

    public CreatorException(String message, Throwable e) {
        super(message, e);
    }
}
