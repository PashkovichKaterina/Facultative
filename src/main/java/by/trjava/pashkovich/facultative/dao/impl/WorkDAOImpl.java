package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.WorkDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class WorkDAOImpl implements WorkDAO {
    @Override
    public void insertWork(int courseId, String title) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_WORK)) {
            statement.setInt(1, courseId);
            statement.setString(2, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteWorkByCourse(int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_WORK_BY_COURSE)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public Set<String> getWorkTitleByCourse(int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Set<String> worksTitle = new LinkedHashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_WORK_TITLE_BY_COURSE)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String title = resultSet.getString(Variable.TITLE);
                    worksTitle.add(title);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return worksTitle;
    }

    @Override
    public int getWorkId(int courseId, String title) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_WORK_ID)) {
            statement.setInt(1, courseId);
            statement.setString(2, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt(Variable.WORK_ID);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return result;
    }
}
