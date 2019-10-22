package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ClassDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.util.CustomFormatForDate;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class ClassDAOImpl implements ClassDAO {

    @Override
    public void insertClass(int courseId, Date date, String time) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_CLASS)) {
            statement.setInt(1, courseId);
            statement.setString(2, CustomFormatForDate.getUseServerDateFormat(date));
            statement.setString(3, time);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void insertClass(int courseId, String date, String time) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_CLASS)) {
            statement.setInt(1, courseId);
            statement.setString(2, date + " " + time);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean isContains(int courseId, Date date) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_CLASS)) {
            statement.setInt(1, courseId);
            statement.setString(2, "%" + CustomFormatForDate.getUseServerDateFormat(date) + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean isContains(int courseId, String date) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_CLASS)) {
            statement.setInt(1, courseId);
            statement.setString(2, "%" + date + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void deleteClassByCourse(int courseId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_CLASS_BY_COURSE)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public int getClassesCountByCourse(int courseId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        int classesCount = 0;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_CLASSES_COUNT_BY_COURSE)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    classesCount = resultSet.getInt(Variable.COUNT);
                }
                return classesCount;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public Set<Date> getClassDateTimeByCourse(int courseId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Date> classes = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_CLASS_BY_COURSE)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Date date = resultSet.getTimestamp(Variable.DATE_TIME);
                    classes.add(date);
                }
                return classes;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public Set<String> getAllDaysOnRu() throws DAOException {
        return getAllDays(SqlQuery.GET_ALL_DAYS_RU);
    }

    @Override
    public Set<String> getAllDaysOnEn() throws DAOException {
        return getAllDays(SqlQuery.GET_ALL_DAYS_EN);
    }

    private Set<String> getAllDays(String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<String> days = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String day = resultSet.getString(Variable.TITLE);
                    days.add(day);
                }
                return days;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }
}
