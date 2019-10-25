package by.trjava.pashkovich.facultative.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class is used to represent the date and time in the required format.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Date
 * @see SimpleDateFormat
 * @since JDK1.0
 */
public class CustomFormatForDate {
    /**
     * Format is used to write information about date to the database and calendar in jsp-page.
     */
    public static final SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Format is used to write information about date to the jsp-page for the user.
     */
    public static final SimpleDateFormat CLIENT_FORMAT_DATE = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Format is used to write information about time to the jsp-page for the user.
     */
    public static final SimpleDateFormat CLIENT_FORMAT_TIME = new SimpleDateFormat("HH:mm");

    private CustomFormatForDate() {
    }

    /**
     * Returns the string representation of date in the format yyyy-MM-dd.
     *
     * @param date object {@code Date}
     * @return the string representation of date in the format yyyy-MM-dd.
     */
    public static String getUseServerDateFormat(Date date) {
        return date == null ? null : SERVER_DATE_FORMAT.format(date);
    }

    /**
     * Returns the string representation of date in the format dd.MM.yyyy.
     *
     * @param date object {@code Date}
     * @return the string representation of date in the format dd.MM.yyyy.
     */
    public static String getUseClientDateFormat(Date date) {
        return date == null ? null : CLIENT_FORMAT_DATE.format(date);
    }

    /**
     * Returns the string representation of time in the format HH:mm.
     *
     * @param date object {@code Date}
     * @return the string representation of time in the format HH:mm.
     */
    public static String getUseClientTimeFormat(Date date) {
        return date == null ? null : CLIENT_FORMAT_TIME.format(date);
    }
}
