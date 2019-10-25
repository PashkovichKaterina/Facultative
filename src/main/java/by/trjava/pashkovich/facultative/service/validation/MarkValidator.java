package by.trjava.pashkovich.facultative.service.validation;

/**
 * Class containing methods for checking values used by {@code MarkService} class.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @since JDK 1.0
 */
public class MarkValidator {
    /**
     * Minimum acceptable mark.
     */
    private static final int MIN_MARK = 0;

    /**
     * Maximum acceptable mark.
     */
    private static final int MAX_MARK = 10;

    /**
     * Checks that the marks is between {@value MIN_MARK} and {@value MAX_MARK}.
     *
     * @param mark mark for verification
     * @return {@code true} mark matches the required parameters.
     */
    public static boolean checkMark(int mark) {
        return mark >= MIN_MARK && mark <= MAX_MARK;
    }
}
