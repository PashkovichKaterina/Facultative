package by.trjava.pashkovich.facultative.util;

import by.trjava.pashkovich.facultative.constants.Variable;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    public static final String ruLocal = "ru";
    public static final String enLocal = "en";

    private ResourceBundle ruBundle = ResourceBundle.getBundle(Variable.LOCAL, new Locale(ruLocal, "RU"));
    private ResourceBundle enBundle = ResourceBundle.getBundle(Variable.LOCAL, new Locale(enLocal, "US"));

    private final static MessageManager INSTANCE = new MessageManager();

    public static MessageManager getInstance() {
        return INSTANCE;
    }

    public String getMessage(String key, String local) {
        if (enLocal.equals(local)) {
            return enBundle.getString(key);
        }
        return ruBundle.getString(key);
    }
}
