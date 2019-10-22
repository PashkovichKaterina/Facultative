package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ClassDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.CustomFormatForDate;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class ClassDAOImpl implements ClassDAO {

    @Override
    public void insertClass(int courseId, Date date, String time) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_CLASS)) {
                statement.setInt(1, courseId);
                statement.setString(2, CustomFormatForDate.getUseServerDateFormat(date));
                statement.setString(3, time);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public void insertClass(int courseId, String date, String time) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_CLASS)) {
                statement.setInt(1, courseId);
                statement.setString(2, date + " " + time);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean isContains(int courseId, Date date) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            boolean result;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_CLASS)) {
                statement.setInt(1, courseId);
                statement.setString(2, "%" + CustomFormatForDate.getUseServerDateFormat(date) + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    result = resultSet.next();
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean isContains(int courseId, String date) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            boolean result;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_CLASS)) {
                statement.setInt(1, courseId);
                statement.setString(2, "%" + date + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    result = resultSet.next();
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteClassByCourse(int courseId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_CLASS_BY_COURSE)) {
                statement.setInt(1, courseId);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public int getClassesCountByCourse(int courseId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            int classesCount = 0;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_CLASSES_COUNT_BY_COURSE)) {
                statement.setInt(1, courseId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        classesCount = resultSet.getInt(Variable.COUNT);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return classesCount;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Date> getClassDateTimeByCourse(int courseId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Date> classes = new HashSet<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_CLASS_BY_COURSE)) {
                statement.setInt(1, courseId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Date date = resultSet.getTimestamp(Variable.DATE_TIME);
                        classes.add(date);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return classes;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<String> getAllDaysOnRu() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<String> days = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_DAYS_RU)) {
                    while (resultSet.next()) {
                        String day = resultSet.getString(Variable.TITLE);
                        days.add(day);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return days;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<String> getAllDaysOnEn() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<String> days = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_DAYS_EN)) {
                    while (resultSet.next()) {
                        String day = resultSet.getString(Variable.TITLE);
                        days.add(day);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return days;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }
}
