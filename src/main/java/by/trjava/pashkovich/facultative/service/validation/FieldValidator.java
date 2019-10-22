package by.trjava.pashkovich.facultative.service.validation;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidator {
    public static boolean checkEmail(String email) {
        Pattern p = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

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

    public static boolean isWholeNumber(String str){
        Pattern p = Pattern.compile("[-+]?\\d+");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean checkTextFieldFormat(String str) {
        Pattern p = Pattern.compile("^[a-zA-Zа-яА-Я]+$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean checkDate(String date) {
        Pattern p = Pattern.compile("^[0-9]{4}\\-(0[1-9]|1[0-2])\\-(0[1-9]|((1|2)[0-9])|3[01])$");
        Matcher m = p.matcher(date);
        return m.matches();
    }

    public static boolean checkPhone(String phone) {
        Pattern p = Pattern.compile("\\+375\\(\\d{2}\\)\\d{3}\\-\\d{2}\\-\\d{2}");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static boolean checkTime(String phone) {
        Pattern p = Pattern.compile("^[0-5][0-9]\\:[0-5][0-9]$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static  boolean checkFormatForTextField(String s){
        return !s.contains("\'");
    }
}
