package by.trjava.pashkovich.facultative.dao.pool;

import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;

import java.sql.Connection;

public interface ConnectionPool {
    void initPoolData() throws ConnectionPoolException;

    Connection getConnection() throws ConnectionPoolException;

    void releaseConnection(Connection connection) throws ConnectionPoolException;

    void destroyPool() throws ConnectionPoolException;
}
