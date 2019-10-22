package by.trjava.pashkovich.facultative.service.validation;

public class MarkValidator {
    public static boolean checkMark(int mark) {
        return mark >= 0 && mark <= 10;
    }
}
