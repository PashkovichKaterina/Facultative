package by.trjava.pashkovich.facultative.dao.pool;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();

    void releaseConnection(Connection connection);
}
