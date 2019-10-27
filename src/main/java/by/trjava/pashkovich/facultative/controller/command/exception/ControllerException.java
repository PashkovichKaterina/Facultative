package by.trjava.pashkovich.facultative.controller.command.exception;

public class ControllerException  extends Exception{
    public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Throwable e) {
        super(e);
    }

    public ControllerException(String message, Throwable e) {
        super(message, e);
    }
}
