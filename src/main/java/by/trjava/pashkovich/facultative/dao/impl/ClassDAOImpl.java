package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ClassDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ClassDAOImpl implements ClassDAO {

    @Override
    public void insertClass(int courseId, String date, String time) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_CLASS)) {
            statement.setInt(1, courseId);
            statement.setString(2, date);
            statement.setString(3, time);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public boolean isContains(int courseId, String date) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        boolean result;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_CLASS)) {
            statement.setInt(1, courseId);
            statement.setString(2, date);
            try (ResultSet resultSet = statement.executeQuery()) {
                result = resultSet.next();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return result;
    }

    @Override
    public void deleteClassByCourse(int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_CLASS_BY_COURSE)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public int getClassesCountByCourse(int courseId) throws DAOException {
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
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return classesCount;
    }

    @Override
    public Map<String, String> getClassDateTimeByCourse(int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Map<String, String> classes = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_CLASS_BY_COURSE)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String date = resultSet.getString(Variable.DATE);
                    String time = resultSet.getString(Variable.TIME);
                    classes.put(date, time);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return classes;
    }
}
