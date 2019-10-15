package by.trjava.pashkovich.facultative.dao.exception;

public class ConnectionPoolException extends Exception {
    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Throwable e) {
        super(e);
    }

    public ConnectionPoolException(String message, Throwable e) {
        super(message, e);
    }
}
