package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.MarkDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MarkDAOImpl implements MarkDAO {

    @Override
    public void insertMark(int studentId, int workId, int mark) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_MARK)) {
            statement.setInt(1, studentId);
            statement.setInt(2, workId);
            statement.setInt(3, mark);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void updateMark(int studentId, int workId, int mark) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_MARK)) {
            statement.setInt(1, mark);
            statement.setInt(2, studentId);
            statement.setInt(3, workId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteMarkByCourse(int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_MARK_BY_COURSE)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public int getStudentAverageMarkByCourse(int studentId, int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        int averageMark = 0;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_STUDENT_AVERAGE_MARK_BY_COURSE)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    averageMark = resultSet.getInt(Variable.MARK);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return averageMark;
    }

    @Override
    public Map<String, Integer> getStudentMarkWithWorkTitleByCourse(int studentId, int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Map<String, Integer> marks = new LinkedHashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_STUDENT_ALL_MARK_BY_COURSE)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String work = resultSet.getString(Variable.TITLE);
                    int mark = resultSet.getInt(Variable.MARK);
                    marks.put(work, mark);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return marks;
    }
}
