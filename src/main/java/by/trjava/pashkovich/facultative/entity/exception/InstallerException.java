package by.trjava.pashkovich.facultative.entity.exception;

public class InstallerException extends Exception {
    public InstallerException() {
        super();
    }

    public InstallerException(String message) {
        super(message);
    }

    public InstallerException(Throwable e) {
        super(e);
    }

    public InstallerException(String message, Throwable e) {
        super(message, e);
    }
}
