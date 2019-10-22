package by.trjava.pashkovich.facultative.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomFormatForDate {
    public static final SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat CLIENT_FORMAT_DATE = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat SERVER_FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat CLIENT_FORMAT_TIME = new SimpleDateFormat("HH:mm");

    private CustomFormatForDate() {
    }

    public static String getUseServerDateFormat(Date date) {
        return date == null ? null : SERVER_DATE_FORMAT.format(date);
    }

    public static String getUseClientDateFormat(Date date) {
        return date == null ? null : CLIENT_FORMAT_DATE.format(date);
    }

    public static String getUseServerTimeFormat(Date date) {
        return date == null ? null : SERVER_FORMAT_TIME.format(date);
    }

    public static String getUseClientTimeFormat(Date date) {
        return date == null ? null : CLIENT_FORMAT_TIME.format(date);
    }


}
