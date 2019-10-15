package by.trjava.pashkovich.facultative.dao.manager;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager INSTANCE = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static DBResourceManager getInstance() {
        return INSTANCE;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
