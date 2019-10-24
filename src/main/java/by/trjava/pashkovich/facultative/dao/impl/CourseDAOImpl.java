package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.CourseDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.*;
import by.trjava.pashkovich.facultative.dao.exception.CreatorException;
import by.trjava.pashkovich.facultative.dao.creator.CourseCreator;
import by.trjava.pashkovich.facultative.dao.creator.CurrentCourseCreator;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public Set<Course> getAllCourseOnRu() throws DAOException {
        return getAllCourse(SqlQuery.GET_ALL_COURSE_RU);
    }

    @Override
    public Set<Course> getAllCourseOnEn() throws DAOException {
        return getAllCourse(SqlQuery.GET_ALL_COURSE_EN);
    }

    private Set<Course> getAllCourse(String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Course> courses = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Course course = new Course();
                    CourseCreator.install(course, resultSet);
                    courses.add(course);
                }
                return courses;
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

    @Override
    public Course getCourseByIdOnRu(int id) throws DAOException {
        return getCourseById(id, SqlQuery.GET_COURSE_BY_ID_RU);
    }

    @Override
    public Course getCourseByIdOnEn(int id) throws DAOException {
        return getCourseById(id, SqlQuery.GET_COURSE_BY_ID_EN);
    }

    private Course getCourseById(int id, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Course course = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    course = new Course();
                    CourseCreator.install(course, resultSet);
                }
                return course;
            }
        } catch (Exception e) {
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
    public Set<Course> getCourseByCategoryOnRu(String category) throws DAOException {
        return getCourseByCategory(category, SqlQuery.GET_COURSE_BY_CATEGORY_RU);
    }

    @Override
    public Set<Course> getCourseByCategoryOnEn(String category) throws DAOException {
        return getCourseByCategory(category, SqlQuery.GET_COURSE_BY_CATEGORY_EN);
    }

    public Set<Course> getCourseByCategory(String category, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Course> courses = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, category);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    CourseCreator.install(course, resultSet);
                    courses.add(course);
                }
                return courses;
            }
        } catch (Exception e) {
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
    public Set<Course> getCourseByTeacherOnRu(int teacherId) throws DAOException {
        return getCourseByTeacher(teacherId, SqlQuery.GET_COURSE_BY_TEACHER_RU);
    }

    @Override
    public Set<Course> getCourseByTeacherOnEn(int teacherId) throws DAOException {
        return getCourseByTeacher(teacherId, SqlQuery.GET_COURSE_BY_TEACHER_EN);
    }

    private Set<Course> getCourseByTeacher(int teacherId, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Course> courses = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    CourseCreator.install(course, resultSet);
                    courses.add(course);
                }
                return courses;
            }
        } catch (Exception e) {
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
    public Set<Course> getCourseByPartialMatchTitleOnRu(String title) throws DAOException {
        return getCourseByPartialMatchTitle(title, SqlQuery.GET_COURSE_BY_PARTIAL_MATCH_TITLE_RU);
    }

    @Override
    public Set<Course> getCourseByPartialMatchTitleOnEn(String title) throws DAOException {
        return getCourseByPartialMatchTitle(title, SqlQuery.GET_COURSE_BY_PARTIAL_MATCH_TITLE_EN);
    }

    private Set<Course> getCourseByPartialMatchTitle(String title, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Course> courses = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + title + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    CourseCreator.install(course, resultSet);
                    courses.add(course);
                }
                return courses;
            }
        } catch (Exception e) {
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
    public Set<String> getAllCategoryOnRu() throws DAOException {
        return getAllCategory(SqlQuery.GET_ALL_CATEGORY_RU);
    }

    @Override
    public Set<String> getAllCategoryOnEn() throws DAOException {
        return getAllCategory(SqlQuery.GET_ALL_CATEGORY_EN);
    }

    private Set<String> getAllCategory(String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<String> categories = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    categories.add(resultSet.getString(Variable.TITLE));
                }
                return categories;
            }
        } catch (Exception e) {
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
    public int getCourseIdByRuTitle(String title) throws DAOException {
        return getCourseIdByTitle(title, SqlQuery.GET_COURSE_ID_BY_RU_TITLE);
    }

    @Override
    public int getCourseIdByEnTitle(String title) throws DAOException {
        return getCourseIdByTitle(title, SqlQuery.GET_COURSE_ID_BY_EN_TITLE);
    }

    private int getCourseIdByTitle(String title, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        int result = -1;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt(Variable.COURSE_ID);
                }
                return result;
            }
        } catch (Exception e) {
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
    public int getCategoryIdByCategoryTitleOnRu(String categoryTitle) throws DAOException {
        return getCategoryIdByCategoryTitle(categoryTitle, SqlQuery.GET_CATEGORY_BY_TITLE_RU);
    }

    @Override
    public int getCategoryIdByCategoryTitleOnEn(String categoryTitle) throws DAOException {
        return getCategoryIdByCategoryTitle(categoryTitle, SqlQuery.GET_CATEGORY_BY_TITLE_EN);
    }

    private int getCategoryIdByCategoryTitle(String categoryTitle, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        int result = -1;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categoryTitle);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt(Variable.CATEGORY_ID);
                }
                return result;
            }
        } catch (Exception e) {
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
    public Map<String, String> getCourseRequirementOnRu(int courseId) throws DAOException {
        return getCourseRequirement(courseId, SqlQuery.GET_COURSE_REQUIREMENT_RU);
    }

    @Override
    public Map<String, String> getCourseRequirementOnEn(int courseId) throws DAOException {
        return getCourseRequirement(courseId, SqlQuery.GET_COURSE_REQUIREMENT_EN);
    }

    private Map<String, String> getCourseRequirement(int courseId, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Map<String, String> require = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    require.put(resultSet.getString(Variable.SKILL), resultSet.getString(Variable.LEVEL));
                }
                return require;
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
    public Map<String, Date> getCourseTimetableOnRu(int courseId) throws DAOException {
        return getCourseTimetable(courseId, SqlQuery.GET_COURSE_TIMETABLE_RU);
    }

    @Override
    public Map<String, Date> getCourseTimetableOnEn(int courseId) throws DAOException {
        return getCourseTimetable(courseId, SqlQuery.GET_COURSE_TIMETABLE_EN);
    }

    private Map<String, Date> getCourseTimetable(int courseId, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Map<String, Date> timeTable = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    timeTable.put(resultSet.getString(Variable.DAY), resultSet.getTime(Variable.TIME));
                }
                return timeTable;
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
    public Set<CurrentCourse> getStudentCurrentCourseOnRu(int studentId) throws DAOException {
        return getStudentCurrentCourse(studentId, SqlQuery.GET_STUDENT_CURRENT_COURSE_RU);
    }

    @Override
    public Set<CurrentCourse> getStudentCurrentCourseOnEn(int studentId) throws DAOException {
        return getStudentCurrentCourse(studentId, SqlQuery.GET_STUDENT_CURRENT_COURSE_EN);
    }

    private Set<CurrentCourse> getStudentCurrentCourse(int studentId, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<CurrentCourse> currentCourses = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CurrentCourse currentCourse = new CurrentCourse();
                    CurrentCourseCreator.install(currentCourse, resultSet);
                    currentCourses.add(currentCourse);
                }
                return currentCourses;
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

    @Override
    public boolean isCourseFixedForTeacher(int courseId, int teacherId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_COURSE_FIXED_FOR_TEACHER)) {
            statement.setInt(1, courseId);
            statement.setInt(2, teacherId);
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
    public void insertCourse(String titleRu, String titleEn, int teacherId, int classCount, int categoryId,
                             boolean availability, String descriptionRu, String descriptionEn) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_COURSE)) {
            statement.setString(1, titleRu);
            statement.setString(2, titleEn);
            statement.setInt(3, teacherId);
            statement.setInt(4, classCount);
            statement.setInt(5, categoryId);
            statement.setBoolean(6, availability);
            statement.setString(7, descriptionRu);
            statement.setString(8, descriptionEn);
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
    public void insertTimetableElementOnRu(int courseId, String day, String time) throws DAOException {
        insertMapElement(courseId, day, time, SqlQuery.INSERT_TIMETABLE_ELEMENT_EN);
    }

    @Override
    public void insertTimetableElementOnEn(int courseId, String day, String time) throws DAOException {
        insertMapElement(courseId, day, time, SqlQuery.INSERT_TIMETABLE_ELEMENT_EN);
    }

    @Override
    public void insertRequirementElementOnRu(int courseId, String skill, String level) throws DAOException {
        insertMapElement(courseId, skill, level, SqlQuery.INSERT_REQUIREMENT_ELEMENT_RU);
    }

    @Override
    public void insertRequirementElementOnEn(int courseId, String skill, String level) throws DAOException {
        insertMapElement(courseId, skill, level, SqlQuery.INSERT_REQUIREMENT_ELEMENT_EN);
    }

    private void insertMapElement(int courseId, String key, String value, String query) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, courseId);
                statement.setString(2, key);
                statement.setString(3, value);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }
}
