package by.trjava.pashkovich.facultative.util;

import by.trjava.pashkovich.facultative.constants.Variable;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class is used to receive the required message in the specified language.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see ResourceBundle
 * @since JDK1.0
 */
public class MessageManager {
    /**
     * Local for Russian language.
     */
    public static final String ruLocal = "ru";

    /**
     * Local for English language.
     */
    public static final String enLocal = "en";

    /**
     * File with Russian text.
     */
    private ResourceBundle ruBundle = ResourceBundle.getBundle(Variable.LOCAL, new Locale(ruLocal, "RU"));

    /**
     * File with English text.
     */
    private ResourceBundle enBundle = ResourceBundle.getBundle(Variable.LOCAL, new Locale(enLocal, "US"));

    private final static MessageManager INSTANCE = new MessageManager();

    public static MessageManager getInstance() {
        return INSTANCE;
    }

    /**
     * Returns a string that matches the given key in the specified language.
     *
     * @param key   need message.
     * @param local need language.
     * @return a string that matches the given key in the specified language.
     */
    public String getMessage(String key, String local) {
        if (enLocal.equals(local)) {
            return enBundle.getString(key);
        }
        return ruBundle.getString(key);
    }
}
