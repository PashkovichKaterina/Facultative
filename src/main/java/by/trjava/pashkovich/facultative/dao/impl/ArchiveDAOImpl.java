package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ArchiveDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.util.CustomFormatForDate;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;
import by.trjava.pashkovich.facultative.entity.installation.CourseInstaller;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class ArchiveDAOImpl implements ArchiveDAO {

    @Override
    public void insertArchive(int studentId, int courseId, int averageMark, Date startDate,
                              Date endDate, String review) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_ARCHIVE)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setInt(3, averageMark);
            statement.setString(4, CustomFormatForDate.getUseServerDateFormat(startDate));
            statement.setString(5, CustomFormatForDate.getUseServerDateFormat(endDate));
            statement.setString(6, review);
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
    public void updateArchive(int studentId, int courseId, int averageMark, Date startDate, Date endDate) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ARCHIVE)) {
            statement.setInt(1, averageMark);
            statement.setString(2, CustomFormatForDate.getUseServerDateFormat(startDate));
            statement.setString(3, CustomFormatForDate.getUseServerDateFormat(endDate));
            statement.setInt(4, studentId);
            statement.setInt(5, courseId);
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
    public void updateReview(int studentId, int courseId, String review) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_REVIEW)) {
            statement.setString(1, review);
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
    public boolean isContains(int studentId, int courseId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_ARCHIVE)) {
            statement.setInt(1, studentId);
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
    public String getReview(int studentId, int courseId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        String result = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_REVIEW_ABOUT_STUDENT)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getString(Variable.REVIEW);
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

    @Override
    public Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudentOnRu(int studentId) throws DAOException {
        return getArchiveCourseWithMarkByStudent(studentId, SqlQuery.GET_ARCHIVE_COURSE_BY_STUDENT_RU);
    }

    @Override
    public Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudentOnEn(int studentId) throws DAOException {
        return getArchiveCourseWithMarkByStudent(studentId, SqlQuery.GET_ARCHIVE_COURSE_BY_STUDENT_EN);
    }

    private Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudent(int studentId, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Map<ArchiveCourse, Integer> archives = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ArchiveCourse archiveCourse = new ArchiveCourse();
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    archiveCourse.setCourse(course);
                    archiveCourse.setBeginDate(resultSet.getDate(Variable.START_DATE));
                    archiveCourse.setEndDate(resultSet.getDate(Variable.END_DATE));
                    int averageMark = resultSet.getInt(Variable.AVERAGE_MARK);
                    archives.put(archiveCourse, averageMark);
                }
                return archives;
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
