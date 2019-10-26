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
import by.trjava.pashkovich.facultative.dao.exception.CreatorException;
import by.trjava.pashkovich.facultative.dao.creator.CourseCreator;
import by.trjava.pashkovich.facultative.dao.creator.UserCreator;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ApplyDAOImpl implements ApplyDAO {
    /**
     * Inserts apply specific student on specific course with specific status
     * used {@code SqlQuery.INSERT_APPLY} SQL query.
     *
     * @param studentId   student id.
     * @param courseId    course id.
     * @param applyStatus apply status.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public void insertApply(int studentId, int courseId, int applyStatus) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_APPLY)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setInt(3, applyStatus);
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
     * Updates apply specific student on specific course with specific status
     * used {@code SqlQuery.UPDATE_APPLY_STATUS} SQL query.
     *
     * @param studentId   student id.
     * @param courseId    course id.
     * @param applyStatus apply status.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Deletes apply by specific student on specific course
     * used {@code SqlQuery.DELETE_APPLY} SQL query.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Deletes all applications from the specific course with the status "Learning"
     * used {@code SqlQuery.DELETE_LEARNING_APPLY} SQL query.
     *
     * @param courseId course id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Checks if the database contains the application of the specified student
     * for the specified course used {@code SqlQuery.IS_CONTAINS_APPLY} SQL query.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return {@code true} if the database contains the application of the specified student
     * for the specified course, {@code false} otherwise.
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
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_APPLY)) {
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
     * Returns all courses on Russian language for which the student applied for together with her status
     * used {@code SqlQuery.GET_APPLY_BY_STUDENT_RU} SQL query.
     *
     * @param studentId student id.
     * @return all courses for which the student applied for together with her status.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<Course, String> getApplyByStudentOnRu(int studentId) throws DAOException {
        return getApplyByStudent(studentId, SqlQuery.GET_APPLY_BY_STUDENT_RU);
    }

    /**
     * Returns all courses on English language for which the student applied for together with her status
     * used {@code SqlQuery.GET_APPLY_BY_STUDENT_EN} SQL query.
     *
     * @param studentId student id.
     * @return all courses for which the student applied for together with her status.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<Course, String> getApplyByStudentOnEn(int studentId) throws DAOException {
        return getApplyByStudent(studentId, SqlQuery.GET_APPLY_BY_STUDENT_EN);
    }

    /**
     * Returns all courses for which the student applied for together with her status used specific query.
     *
     * @param studentId student id.
     * @param query     specific query.
     * @return all courses for which the student applied for together with her status.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
                    Course course = CourseCreator.create(resultSet);
                    String status = resultSet.getString(Variable.STATUS);
                    applies.put(course, status);
                }
            }
            return applies;
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

    /**
     * Returns all application on Russian stored in the database
     * used {@code SqlQuery.GET_ALL_APPLY_RU} SQL query.
     *
     * @return all application on Russian stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Apply> getAllApplyOnRu() throws DAOException {
        return getAllApply(SqlQuery.GET_ALL_APPLY_RU);
    }

    /**
     * Returns all application on English stored in the database
     * used {@code SqlQuery.GET_ALL_APPLY_EN} SQL query.
     *
     * @return all application on English stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Apply> getAllApplyOnEn() throws DAOException {
        return getAllApply(SqlQuery.GET_ALL_APPLY_EN);
    }

    /**
     * Returns all application stored in the database used specific query.
     *
     * @param query specific query.
     * @return all application stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
                    Student student = (Student) UserCreator.create(resultSet);
                    Course course = CourseCreator.create(resultSet);
                    apply.setStudent(student);
                    apply.setCourse(course);
                    apply.setStatus(resultSet.getString(Variable.STATUS));
                    applies.add(apply);
                }
                return applies;
            }
        } catch (SQLException | CreatorException e) {
            System.out.println(e.getMessage());
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
     * Returns all student applications on Russian language by his name
     * used {@code SqlQuery.GET_ALL_APPLY_BY_STUDENT_NAME_RU} SQL query.
     *
     * @param studentName student name.
     * @return all student applications by his name.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Apply> getAllApplyByStudentNameOnRu(String studentName) throws DAOException {
        return getAllApplyByTextPartialMatch(studentName, SqlQuery.GET_ALL_APPLY_BY_STUDENT_NAME_RU);
    }

    /**
     * Returns all student applications on English language by his name
     * used {@code SqlQuery.GET_ALL_APPLY_BY_STUDENT_NAME_EN} SQL query.
     *
     * @param studentName student name.
     * @return all student applications by his name.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Apply> getAllApplyByStudentNameOnEn(String studentName) throws DAOException {
        return getAllApplyByTextPartialMatch(studentName, SqlQuery.GET_ALL_APPLY_BY_STUDENT_NAME_EN);
    }

    /**
     * Returns all student applications on Russian language by course title
     * used {@code SqlQuery.GET_ALL_APPLY_BY_COURSE_TITLE_RU} SQL query.
     *
     * @param studentName student name.
     * @return all student applications on Russian language by course title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Apply> getAllApplyByCourseTitleOnRu(String studentName) throws DAOException {
        return getAllApplyByTextPartialMatch(studentName, SqlQuery.GET_ALL_APPLY_BY_COURSE_TITLE_RU);
    }

    /**
     * Returns all student applications on English language by course title
     * used {@code SqlQuery.GET_ALL_APPLY_BY_COURSE_TITLE_EN} SQL query.
     *
     * @param studentName student name.
     * @return all student applications on English language by course title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Apply> getAllApplyByCourseTitleOnEn(String studentName) throws DAOException {
        return getAllApplyByTextPartialMatch(studentName, SqlQuery.GET_ALL_APPLY_BY_COURSE_TITLE_EN);
    }

    /**
     * Returns all student applications on English language by text field used specific query.
     *
     * @param parameter student name.
     * @param query     specific query.
     * @return all student applications by text field.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private Set<Apply> getAllApplyByTextPartialMatch(String parameter, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Apply> applies = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + parameter + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Apply apply = new Apply();
                    Student student = (Student) UserCreator.create(resultSet);
                    apply.setStudent(student);
                    Course course = CourseCreator.create(resultSet);
                    apply.setCourse(course);
                    apply.setStatus(resultSet.getString(Variable.STATUS));
                    applies.add(apply);
                }
                return applies;
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
