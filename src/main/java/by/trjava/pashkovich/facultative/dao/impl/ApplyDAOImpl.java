package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ApplyDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.characteristic.ApplyStatus;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;
import by.trjava.pashkovich.facultative.entity.installation.CourseInstaller;
import by.trjava.pashkovich.facultative.entity.installation.UserInstaller;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ApplyDAOImpl implements ApplyDAO {

    @Override
    public void insertApply(int userId, int courseId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_APPLY)) {
            statement.setInt(1, userId);
            statement.setInt(2, courseId);
            statement.setInt(3, ApplyStatus.FIELD_APPLIED.getStatusId());
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
    public void updateApplyStatus(int studentId, int courseId, int applyStatus) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_APPLY_STATUS)) {
            statement.setInt(1, applyStatus);
            statement.setInt(2, studentId);
            statement.setInt(3, courseId);
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
    public void deleteApply(int studentId, int courseId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_APPLY)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
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
    public void deleteAllLearnApplyByCourse(int courseId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_LEARNING_APPLY)) {
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
    public boolean isContains(int userId, int courseId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_APPLY)) {
            statement.setInt(1, userId);
            statement.setInt(2, courseId);
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
    public Map<Course, String> getApplyByStudentOnRu(int studentId) throws DAOException {
        return getApplyByStudent(studentId, SqlQuery.GET_APPLY_BY_STUDENT_RU);
    }

    @Override
    public Map<Course, String> getApplyByStudentOnEn(int studentId) throws DAOException {
        return getApplyByStudent(studentId, SqlQuery.GET_APPLY_BY_STUDENT_EN);
    }

    private Map<Course, String> getApplyByStudent(int studentId, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Map<Course, String> applies = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    String status = resultSet.getString(Variable.STATUS);
                    applies.put(course, status);
                }
            }
            return applies;
        } catch (SQLException | InstallerException e) {
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
    public Set<Apply> getAllApplyOnRu() throws DAOException {
        return getAllApply(SqlQuery.GET_ALL_APPLY_RU);
    }

    @Override
    public Set<Apply> getAllApplyOnEn() throws DAOException {
        return getAllApply(SqlQuery.GET_ALL_APPLY_EN);
    }

    private Set<Apply> getAllApply(String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Apply> applies = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Apply apply = new Apply();
                    Student student = new Student();
                    UserInstaller.install(student, resultSet);
                    apply.setStudent(student);
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    apply.setCourse(course);
                    apply.setStatus(resultSet.getString(Variable.STATUS));
                    applies.add(apply);
                }
                return applies;
            }
        } catch (SQLException | InstallerException e) {
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
    public Set<Apply> getAllApplyByStudentNameOnRu(String studentName) throws DAOException {
        return getAllApplyByTextPartialMatch(studentName, SqlQuery.GET_ALL_APPLY_BY_STUDENT_NAME_RU);
    }

    @Override
    public Set<Apply> getAllApplyByStudentNameOnEn(String studentName) throws DAOException {
        return getAllApplyByTextPartialMatch(studentName, SqlQuery.GET_ALL_APPLY_BY_STUDENT_NAME_EN);
    }

    @Override
    public Set<Apply> getAllApplyByCourseTitleOnRu(String studentName) throws DAOException {
        return getAllApplyByTextPartialMatch(studentName, SqlQuery.GET_ALL_APPLY_BY_COURSE_TITLE_RU);
    }

    @Override
    public Set<Apply> getAllApplyByCourseTitleOnEn(String studentName) throws DAOException {
        return getAllApplyByTextPartialMatch(studentName, SqlQuery.GET_ALL_APPLY_BY_COURSE_TITLE_EN);
    }

    private Set<Apply> getAllApplyByTextPartialMatch(String studentName, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Apply> applies = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + studentName + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Apply apply = new Apply();
                    Student student = new Student();
                    UserInstaller.install(student, resultSet);
                    apply.setStudent(student);
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    apply.setCourse(course);
                    apply.setStatus(resultSet.getString(Variable.STATUS));
                    applies.add(apply);
                }
                return applies;
            }
        } catch (SQLException | InstallerException e) {
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
