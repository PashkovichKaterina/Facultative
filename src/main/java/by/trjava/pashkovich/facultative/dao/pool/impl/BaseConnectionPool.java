package by.trjava.pashkovich.facultative.dao.pool.impl;

import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.parameter.DBResourceManager;
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
    }

    public static BaseConnectionPool getInstance() {
        return instance;
    }

    public void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
            usingQueue = new ArrayBlockingQueue<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Can't find database driver class");
        } catch (SQLException e) {
            throw new ConnectionPoolException("Database access error while creating connection");
        }
    }

    @Override
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        if (connectionQueue.size() != 0) {
            try {
                connection = connectionQueue.take();
                usingQueue.add(connection);
            } catch (InterruptedException e) {
                throw new ConnectionPoolException("Free connection timeout is over", e);
            }
        }
        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if (usingQueue.contains(connection)) {
            usingQueue.remove(connection);
            connectionQueue.add(connection);
        } else {
            throw new ConnectionPoolException("Cannot put connection which was created outside the pool");
        }
    }

    @Override
    public void destroyPool() throws ConnectionPoolException {
        for (Connection connection : connectionQueue) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionPoolException("Cannot close connection");
            }
        }
    }
}
