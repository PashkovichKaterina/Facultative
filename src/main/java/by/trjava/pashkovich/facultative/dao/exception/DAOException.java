package by.trjava.pashkovich.facultative.dao.exception;

public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable e) {
        super(e);
    }

    public DAOException(String message, Throwable e) {
        super(message, e);
    }
}
