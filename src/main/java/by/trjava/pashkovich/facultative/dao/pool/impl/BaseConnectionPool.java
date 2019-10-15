package by.trjava.pashkovich.facultative.dao.pool.impl;

import by.trjava.pashkovich.facultative.dao.manager.DBResourceManager;
import by.trjava.pashkovich.facultative.dao.parameter.DBParameter;
import by.trjava.pashkovich.facultative.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BaseConnectionPool implements ConnectionPool {
    public static final int POOL_SIZE = 20;

    private static final BaseConnectionPool instance = new BaseConnectionPool();

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> usingQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;

    private BaseConnectionPool() {
        DBResourceManager resourceManager = DBResourceManager.getInstance();
        driverName = resourceManager.getValue(DBParameter.DB_DRIVER);
        url = resourceManager.getValue(DBParameter.DB_URL);
        user = resourceManager.getValue(DBParameter.DB_USER);
        password = resourceManager.getValue(DBParameter.DB_PASSWORD);

        initPoolData();
    }

    public static BaseConnectionPool getInstance() {
        return instance;
    }

    public void initPoolData() {
        try {
            Class.forName(driverName);
            connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
            usingQueue = new ArrayBlockingQueue<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(connection);
            }
        } catch (SQLException e) {
            //throw new ConnectionPoolException("Database access error while creating connection", e);
        } catch (ClassNotFoundException e) {
            //throw new ConnectionPoolException("Can't find database driver class", e);
        }
    }

    @Override
    public Connection getConnection(){
        Connection connection = null;
        if (connectionQueue.size() != 0) {
            try {
                connection = connectionQueue.take();
                usingQueue.add(connection);
            } catch (InterruptedException e) {
                //throw new ConnectionPoolException("Error connecting to the data source.", e);
            }
        }
        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) {
        if (usingQueue.contains(connection)) {
            usingQueue.remove(connection);
            connectionQueue.add(connection);
        }
    }
}
