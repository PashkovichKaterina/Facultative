package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ClassDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DatabaseClassDAOImpl implements ClassDAO {
    /**
     * Inserts class for specific course
     * used {@code SqlQuery.INSERT_CLASS} SQL query.
     *
     * @param courseId course id.
     * @param date     class date.
     * @param time     class time.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Checks if at this course there is already a lesson on the specified date,
     * used {@code SqlQuery.IS_CONTAINS_CLASS} SQL query.
     *
     * @param courseId course id.
     * @param date     class date.
     * @return {@code true} if at this course there is already a lesson on the specified date,
     * {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Delete all class by specific course,
     * used {@code SqlQuery.DELETE_CLASS_BY_COURSE} SQL query.
     *
     * @param courseId course id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Returns classes count by specific course,
     * used {@code SqlQuery.GET_CLASSES_COUNT_BY_COURSE} SQL query.
     *
     * @param courseId course id.
     * @return classes count by specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Returns returns the date and time of all classes at the specified course,
     * used {@code SqlQuery.GET_CLASS_BY_COURSE} SQL query.
     *
     * @param courseId course id.
     * @return the date and time of all classes at the specified course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Returns all days in Russian on which classes from the database can be held,
     * used {@code SqlQuery.GET_ALL_DAYS_RU} SQL query.
     *
     * @return all days in Russian on which classes from the database can be held.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<String> getAllDaysOnRu() throws DAOException {
        return getAllDays(SqlQuery.GET_ALL_DAYS_RU);
    }

    /**
     * Returns all days in English on which classes from the database can be held,
     * used {@code SqlQuery.GET_ALL_DAYS_EN} SQL query.
     *
     * @return all days in English on which classes from the database can be held.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<String> getAllDaysOnEn() throws DAOException {
        return getAllDays(SqlQuery.GET_ALL_DAYS_EN);
    }

    /**
     * Returns all days on which classes from the database can be held, used specific query.
     *
     * @param query specific query.
     * @return all days in English on which classes from the database can be held.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private Set<String> getAllDays(String query) throws DAOException {
        Connection connection;
        Set<String> days = new LinkedHashSet<>();
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String day = resultSet.getString(Variable.TITLE);
                    days.add(day);
                }
            }
            return days;
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

    /**
     * Returns begin date of the specific course,
     * used {@code SqlQuery.GET_BEGIN_DATE_BY_COURSE} SQL query.
     *
     * @param courseId course id.
     * @return begin date of the specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Date getBeginDateByCourse(int courseId) throws DAOException {
        return getDateByCourse(courseId, SqlQuery.GET_BEGIN_DATE_BY_COURSE);
    }

    /**
     * Returns end date of the specific course,
     * used {@code SqlQuery.GET_END_DATE_BY_COURSE} SQL query.
     *
     * @param courseId course id.
     * @return end date of the specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Date getEndDateByCourse(int courseId) throws DAOException {
        return getDateByCourse(courseId, SqlQuery.GET_END_DATE_BY_COURSE);
    }

    /**
     * Returns date of the specific course by specific query.
     *
     * @param courseId course id.
     * @param query    specific query.
     * @return date of the specific course by specific query.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private Date getDateByCourse(int courseId, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                Date result = null;
                if (resultSet.next()) {
                    result = resultSet.getTimestamp(Variable.DATE);
                }
                return result;
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
