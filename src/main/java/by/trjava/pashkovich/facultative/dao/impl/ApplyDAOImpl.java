package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ApplyDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.characteristic.ApplyStatus;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;
import by.trjava.pashkovich.facultative.entity.installation.CourseInstaller;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ApplyDAOImpl implements ApplyDAO {

    @Override
    public void insertApply(int userId, int courseId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_APPLY)) {
                statement.setInt(1, userId);
                statement.setInt(2, courseId);
                statement.setInt(3, ApplyStatus.FIELD_APPLIED.getStatusId());
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
    public void updateApplyStatus(int studentId, int courseId, String applyStatus) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_APPLY_STATUS)) {
                statement.setString(1, applyStatus);
                statement.setInt(2, studentId);
                statement.setInt(3, courseId);
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
    public void deleteApply(int studentId, int courseId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_APPLY)) {
                statement.setInt(1, studentId);
                statement.setInt(2, courseId);
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
    public boolean isContains(int userId, int courseId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            boolean isContain;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_APPLY)) {
                statement.setInt(1, userId);
                statement.setInt(2, courseId);
                ResultSet resultSet = statement.executeQuery();
                isContain = resultSet.next();
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return isContain;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }


    @Override
    public Map<Course, String> getApplyByStudentOnRu(int studentId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<Course, String> applies = new HashMap<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_APPLY_BY_STUDENT_RU)) {
                statement.setInt(1, studentId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Course course = new Course();
                        CourseInstaller.install(course, resultSet);
                        String status = resultSet.getString(Variable.STATUS);
                        applies.put(course, status);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return applies;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<Course, String> getApplyByStudentOnEn(int studentId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<Course, String> applies = new HashMap<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_APPLY_BY_STUDENT_EN)) {
                statement.setInt(1, studentId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Course course = new Course();
                        CourseInstaller.install(course, resultSet);
                        String status = resultSet.getString(Variable.STATUS);
                        applies.put(course, status);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return applies;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }
}
