package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ArchiveDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.CustomFormatForDate;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;
import by.trjava.pashkovich.facultative.entity.installation.CourseInstaller;

import java.sql.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    public Set<ArchiveCourse> getArchiveCourseByStudentOnRu(int studentId) throws DAOException {
        return getArchiveCourseByStudent(studentId, SqlQuery.GET_ARCHIVE_COURSE_BY_STUDENT_RU);
    }

    @Override
    public Set<ArchiveCourse> getArchiveCourseByStudentOnEn(int studentId) throws DAOException {
        return getArchiveCourseByStudent(studentId, SqlQuery.GET_ARCHIVE_COURSE_BY_STUDENT_EN);
    }

    private Set<ArchiveCourse> getArchiveCourseByStudent(int studentId, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<ArchiveCourse> archives = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ArchiveCourse archiveCourse = new ArchiveCourse();
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    archiveCourse.setCourse(course);
                    archiveCourse.setBeginDate(resultSet.getDate(Variable.START_DATE));
                    archiveCourse.setEndDate(resultSet.getDate(Variable.END_DATE));
                    archives.add(archiveCourse);
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
