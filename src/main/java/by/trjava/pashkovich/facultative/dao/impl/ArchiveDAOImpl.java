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
import by.trjava.pashkovich.facultative.dao.exception.CreatorException;
import by.trjava.pashkovich.facultative.dao.creator.CourseCreator;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class ArchiveDAOImpl implements ArchiveDAO {
    /**
     * Inserts information about the course taken by the student
     * used {@code SqlQuery.INSERT_ARCHIVE} SQL query.
     *
     * @param studentId   student id.
     * @param courseId    course id.
     * @param averageMark student average mark.
     * @param startDate   course start date.
     * @param endDate     course end date.
     * @param review      review about student work.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Inserts information about the course taken by the student
     * used {@code SqlQuery.UPDATE_ARCHIVE} SQL query.
     *
     * @param studentId   student id.
     * @param courseId    course id.
     * @param averageMark student average mark.
     * @param startDate   course start date.
     * @param endDate     course end date.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Update review about student work on specific course
     * used {@code SqlQuery.UPDATE_REVIEW} SQL query.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @param review    review about student work.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Checks if the archive contains information about the specified student
     * at the specified course used {@code SqlQuery.IS_CONTAINS_ARCHIVE} SQL query.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return {@code true} if the archive contains information about the specified student
     * at the specified course, {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Returns review about specific student by specific course
     * used {@code SqlQuery.GET_REVIEW_ABOUT_STUDENT} SQL query.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return review about specific student by specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Returns all archive course on Russian language with average mark for specific student
     * used {@code SqlQuery.GET_ARCHIVE_COURSE_BY_STUDENT_RU} SQL query.
     *
     * @param studentId student id.
     * @return all archive course on Russian language with average mark for specific student.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudentOnRu(int studentId) throws DAOException {
        return getArchiveCourseWithMarkByStudent(studentId, SqlQuery.GET_ARCHIVE_COURSE_BY_STUDENT_RU);
    }

    /**
     * Returns all archive course on English language with average mark for specific student
     * used {@code SqlQuery.GET_ARCHIVE_COURSE_BY_STUDENT_EN} SQL query.
     *
     * @param studentId student id.
     * @return all archive course on English language with average mark for specific student.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudentOnEn(int studentId) throws DAOException {
        return getArchiveCourseWithMarkByStudent(studentId, SqlQuery.GET_ARCHIVE_COURSE_BY_STUDENT_EN);
    }

    /**
     * Returns all archive course with average mark for specific student used specific query.
     *
     * @param studentId student id.
     * @param query     specific query.
     * @return
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
                    Course course = CourseCreator.create(resultSet);
                    archiveCourse.setCourse(course);
                    archiveCourse.setBeginDate(resultSet.getDate(Variable.START_DATE));
                    archiveCourse.setEndDate(resultSet.getDate(Variable.END_DATE));
                    int averageMark = resultSet.getInt(Variable.AVERAGE_MARK);
                    archives.put(archiveCourse, averageMark);
                }
                return archives;
            }
        } catch (SQLException | CreatorException e) {
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
