package by.trjava.pashkovich.facultative.service.validation;

import java.util.Collection;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class containing methods for checking basic field.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @since JDK 1.0
 */
public class FieldValidator {
    /**
     * Checks if email meets the requirements.
     *
     * @param email email for verification.
     * @return {@code true} if email meets the requirements, {@code false} otherwise.
     */
    public static boolean checkEmail(String email) {
        Pattern p = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Checks if the string contains a whole number.
     *
     * @param str string for verification.
     * @return {@code true} if the string contains a whole number, {@code false} otherwise.
     */
    public static boolean isWholeNumber(String str) {
        Pattern p = Pattern.compile("[-+]?\\d+");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * Checks if the string meets the requirements.
     *
     * @param str string for verification.
     * @return {@code true} if the string meets the requirements, {@code false} otherwise.
     */
    public static boolean checkTextFieldFormat(String str) {
        Pattern p = Pattern.compile("^[A-zА-я]+$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * Checks if the string meets the date requirements.
     *
     * @param date string for verification.
     * @return {@code true} if the string meets the date requirements, {@code false} otherwise.
     */
    public static boolean checkDate(String date) {
        Pattern p = Pattern.compile("^[0-9]{4}\\-(0[1-9]|1[0-2])\\-(0[1-9]|((1|2)[0-9])|3[01])$");
        Matcher m = p.matcher(date);
        return m.matches();
    }

    /**
     * Checks if the string meets the phone requirements.
     *
     * @param phone string for verification.
     * @return {@code true} if the string meets the phone requirements, {@code false} otherwise.
     */
    public static boolean checkPhone(String phone) {
        Pattern p = Pattern.compile("\\+375\\(\\d{2}\\)\\d{3}\\-\\d{2}\\-\\d{2}");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * Checks if the string meets the time requirements.
     *
     * @param time string for verification.
     * @return {@code true} if the string meets the time requirements, {@code false} otherwise.
     */
    public static boolean checkTime(String time) {
        Pattern p = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
        Matcher m = p.matcher(time);
        return m.matches();
    }

    /**
     * Checks if the string meets the requirements.
     *
     * @param s string for verification.
     * @return {@code true} if the string meets the requirements, {@code false} otherwise.
     */
    public static boolean checkFormatForTextField(String s) {
        return !s.contains("\'");
    }

    /**
     * Checks if the object is not empty.
     *
     * @param object string for verification.
     * @return {@code true} if tif the object is not empty, {@code false} otherwise.
     */
    public static boolean isEmpty(Object object) {
        if (!Optional.ofNullable(object).isPresent()) {
            return true;
        }
        if (object instanceof String && ((String) object).length() == 0) {
            return true;
        }
        if (object instanceof Collection && ((Collection) object).isEmpty()) {
            return true;
        }
        return false;
    }
}
