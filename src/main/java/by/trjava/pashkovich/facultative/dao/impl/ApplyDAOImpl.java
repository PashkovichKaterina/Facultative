package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ApplyDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.characteristics.ApplyStatus;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;
import by.trjava.pashkovich.facultative.entity.installation.CourseInstaller;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ApplyDAOImpl implements ApplyDAO {

    @Override
    public void insertApply(int userId, int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_APPLY)) {
            statement.setInt(1, userId);
            statement.setInt(2, courseId);
            statement.setInt(3, ApplyStatus.FIELD_APPLIED.getStatusId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void updateApplyStatus(int studentId, int courseId, ApplyStatus applyStatus) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_APPLY_STATUS)) {
            statement.setInt(1, applyStatus.getStatusId());
            statement.setInt(2, studentId);
            statement.setInt(3, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteApply(int studentId, int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_APPLY)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public boolean isContains(int userId, int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        boolean isContain;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_APPLY)) {
            statement.setInt(1, userId);
            statement.setInt(2, courseId);
            ResultSet resultSet = statement.executeQuery();
            isContain = resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return isContain;
    }


    @Override
    public Set<Apply> getApplyByStudent(Student student) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Set<Apply> requests = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_APPLY_BY_STUDENT)) {
            statement.setInt(1, student.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Apply apply = new Apply();
                    apply.setUser(student);
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    apply.setCourse(course);
                    apply.setStatus(ApplyStatus.getStatus(resultSet.getInt(Variable.STATUS_ID)));
                    requests.add(apply);
                }
            }
        } catch (SQLException |InstallerException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return requests;
    }
}
