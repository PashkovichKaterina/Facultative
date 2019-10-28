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

public class DatabaseCourseDAOImpl implements CourseDAO {
    /**
     * Returns all course on Russian are stored in the database,
     * used {@code SqlQuery.GET_ALL_COURSE_RU} SQL query.
     *
     * @return all course on Russian are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Course> getAllCourseOnRu() throws DAOException {
        return getAllCourse(SqlQuery.GET_ALL_COURSE_RU);
    }

    /**
     * Returns all course on English are stored in the database,
     * used {@code SqlQuery.GET_ALL_COURSE_EN} SQL query.
     *
     * @return all course on English are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Course> getAllCourseOnEn() throws DAOException {
        return getAllCourse(SqlQuery.GET_ALL_COURSE_EN);
    }

    /**
     * Returns all course are stored in the database, used specific query.
     *
     * @param query specific query.
     * @return all course are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
                    Course course = CourseCreator.create(resultSet);
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

    /**
     * Returns course on Russian with specific id,
     * used {@code SqlQuery.GET_COURSE_BY_ID_RU} SQL query.
     *
     * @param id course id.
     * @return course on Russian with specific id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Course getCourseByIdOnRu(int id) throws DAOException {
        return getCourseById(id, SqlQuery.GET_COURSE_BY_ID_RU);
    }

    /**
     * Returns course on English with specific id,
     * used {@code SqlQuery.GET_COURSE_BY_ID_EN} SQL query.
     *
     * @param id course id.
     * @return course on English with specific id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Course getCourseByIdOnEn(int id) throws DAOException {
        return getCourseById(id, SqlQuery.GET_COURSE_BY_ID_EN);
    }

    /**
     * Returns course with specific id used specific query.
     *
     * @param id course id.
     * @return course with specific id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
                    course = CourseCreator.create(resultSet);
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

    /**
     * Returns all course on Russian with specific category title,
     * used {@code SqlQuery.GET_COURSE_BY_CATEGORY_RU} SQL query.
     *
     * @param category category title.
     * @return all course on Russian with specific category title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Course> getCourseByCategoryOnRu(String category) throws DAOException {
        return getCourseByCategory(category, SqlQuery.GET_COURSE_BY_CATEGORY_RU);
    }

    /**
     * Returns all course on English with specific category title,
     * used {@code SqlQuery.GET_COURSE_BY_CATEGORY_EN} SQL query.
     *
     * @param category category title.
     * @return all course on English with specific category title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Course> getCourseByCategoryOnEn(String category) throws DAOException {
        return getCourseByCategory(category, SqlQuery.GET_COURSE_BY_CATEGORY_EN);
    }

    /**
     * Returns all course with specific category title used specific query.
     *
     * @param category category title.
     * @param query    specific query.
     * @return all course with specific category title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
                    Course course = CourseCreator.create(resultSet);
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

    /**
     * Returns all course on Russian with specific teacher,
     * used {@code SqlQuery.GET_COURSE_BY_TEACHER_RU} SQL query.
     *
     * @param teacherId teacher id.
     * @return all course on Russian with specific teacher.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Course> getCourseByTeacherOnRu(int teacherId) throws DAOException {
        return getCourseByTeacher(teacherId, SqlQuery.GET_COURSE_BY_TEACHER_RU);
    }

    /**
     * Returns all course on English with specific teacher,
     * used {@code SqlQuery.GET_COURSE_BY_TEACHER_EN} SQL query.
     *
     * @param teacherId teacher id.
     * @return all course on English with specific teacher.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Course> getCourseByTeacherOnEn(int teacherId) throws DAOException {
        return getCourseByTeacher(teacherId, SqlQuery.GET_COURSE_BY_TEACHER_EN);
    }

    /**
     * Returns all course with specific teacher, used specific query.
     *
     * @param teacherId teacher id.
     * @param query     specific query.
     * @return all course on English with specific teacher.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
                    Course course = CourseCreator.create(resultSet);
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

    /**
     * Returns all course on Russian with specific partial course title,
     * used {@code SqlQuery.GET_COURSE_BY_PARTIAL_MATCH_TITLE_RU} SQL query.
     *
     * @param title partial course title.
     * @return all course on Russian with specific partial course title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Course> getCourseByPartialMatchTitleOnRu(String title) throws DAOException {
        return getCourseByPartialMatchTitle(title, SqlQuery.GET_COURSE_BY_PARTIAL_MATCH_TITLE_RU);
    }

    /**
     * Returns all course on English with specific partial course title,
     * used {@code SqlQuery.GET_COURSE_BY_PARTIAL_MATCH_TITLE_EN} SQL query.
     *
     * @param title partial course title.
     * @return all course on English with specific partial course title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Course> getCourseByPartialMatchTitleOnEn(String title) throws DAOException {
        return getCourseByPartialMatchTitle(title, SqlQuery.GET_COURSE_BY_PARTIAL_MATCH_TITLE_EN);
    }

    /**
     * Returns all course with specific partial course title used specific query.
     *
     * @param title partial course title.
     * @return all course with specific partial course title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
                    Course course = CourseCreator.create(resultSet);
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

    /**
     * Returns course id with specific title,
     * used {@code SqlQuery.GET_COURSE_ID_BY_RU_TITLE} SQL query.
     *
     * @param title work title on Russian.
     * @return course id specific title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public int getCourseIdByRuTitle(String title) throws DAOException {
        return getCourseIdByTitle(title, SqlQuery.GET_COURSE_ID_BY_RU_TITLE);
    }

    /**
     * Returns course id wirh specific title,
     * used {@code SqlQuery.GET_COURSE_ID_BY_EN_TITLE} SQL query.
     *
     * @param title work title on English.
     * @return course id specific title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public int getCourseIdByEnTitle(String title) throws DAOException {
        return getCourseIdByTitle(title, SqlQuery.GET_COURSE_ID_BY_EN_TITLE);
    }

    /**
     * Returns course id with specific title, used specific query.
     *
     * @param title work title on English.
     * @return course id specific title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Returns course requirement on Russian in the format skill-level,
     * used {@code SqlQuery.GET_COURSE_REQUIREMENT_RU} SQL query.
     *
     * @param courseId course id.
     * @return course requirement in the format skill-level.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<String, String> getCourseRequirementOnRu(int courseId) throws DAOException {
        return getCourseRequirement(courseId, SqlQuery.GET_COURSE_REQUIREMENT_RU);
    }

    /**
     * Returns course requirement on English in the format skill-level,
     * used {@code SqlQuery.GET_COURSE_REQUIREMENT_EN} SQL query.
     *
     * @param courseId course id.
     * @return course requirement in the format skill-level.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<String, String> getCourseRequirementOnEn(int courseId) throws DAOException {
        return getCourseRequirement(courseId, SqlQuery.GET_COURSE_REQUIREMENT_EN);
    }

    /**
     * Returns course requirement in the format skill-level, used specific query.
     *
     * @param courseId course id.
     * @param query    specific query.
     * @return course requirement in the format skill-level.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Returns course timetable on Russian in the format day-date,
     * used {@code SqlQuery.GET_COURSE_TIMETABLE_RU} SQL query.
     *
     * @param courseId course id.
     * @return course timetable on Russian in the format day-date.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<String, Date> getCourseTimetableOnRu(int courseId) throws DAOException {
        return getCourseTimetable(courseId, SqlQuery.GET_COURSE_TIMETABLE_RU);
    }

    /**
     * Returns course timetable on English in the format day-date,
     * used {@code SqlQuery.GET_COURSE_TIMETABLE_EN} SQL query.
     *
     * @param courseId course id.
     * @return course timetable on English in the format day-date.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<String, Date> getCourseTimetableOnEn(int courseId) throws DAOException {
        return getCourseTimetable(courseId, SqlQuery.GET_COURSE_TIMETABLE_EN);
    }

    /**
     * Returns course timetable in the format day-date, used specific query.
     *
     * @param courseId course id.
     * @param query    specific query.
     * @return course timetable in the format day-date, used specific query.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Returns the course on Russian at which the specified student is studying,
     * used {@code SqlQuery.GET_STUDENT_CURRENT_COURSE_RU} SQL query.
     *
     * @param studentId student id.
     * @return the course on Russian at which the specified student is studying.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<CurrentCourse> getStudentCurrentCourseOnRu(int studentId) throws DAOException {
        return getStudentCurrentCourse(studentId, SqlQuery.GET_STUDENT_CURRENT_COURSE_RU);
    }

    /**
     * Returns the course on English at which the specified student is studying,
     * used {@code SqlQuery.GET_STUDENT_CURRENT_COURSE_EN} SQL query.
     *
     * @param studentId student id.
     * @return the course on English at which the specified student is studying.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<CurrentCourse> getStudentCurrentCourseOnEn(int studentId) throws DAOException {
        return getStudentCurrentCourse(studentId, SqlQuery.GET_STUDENT_CURRENT_COURSE_EN);
    }

    /**
     * Returns the course at which the specified student is studying, used specific query.
     *
     * @param studentId student id.
     * @return the course at which the specified student is studying, used specific query.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
                    CurrentCourse currentCourse = CurrentCourseCreator.create(resultSet);
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

    /**
     * Checks if the specific course is fixed by specific teacher,
     * used {@code SqlQuery.IS_COURSE_FIXED_FOR_TEACHER} SQL query.
     *
     * @param courseId  course id.
     * @param teacherId teacher id.l
     * @return {@code true} if the specific course is fixed by specific teacher, {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Inserts a course with the specified parameters into the database,
     * used {@code SqlQuery.INSERT_COURSE} SQL query.
     *
     * @param titleRu       course title on Russian.
     * @param titleEn       course title on English.
     * @param teacherId     teacher id.
     * @param classCount    course class count.
     * @param categoryId    category id.
     * @param availability  availability.
     * @param descriptionRu description on Russian.
     * @param descriptionEn description on English.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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

    /**
     * Inserts a schedule in Russian at the specified course into the database,
     * used {@code SqlQuery.INSERT_TIMETABLE_ELEMENT_RU} SQL query.
     *
     * @param courseId course id.
     * @param day      class date.
     * @param time     class time.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public void insertTimetableElementOnRu(int courseId, String day, String time) throws DAOException {
        insertMapElement(courseId, day, time, SqlQuery.INSERT_TIMETABLE_ELEMENT_RU);
    }

    /**
     * Inserts a schedule in English at the specified course into the database,
     * used {@code SqlQuery.INSERT_TIMETABLE_ELEMENT_EN} SQL query.
     *
     * @param courseId course id.
     * @param day      class date.
     * @param time     class time.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public void insertTimetableElementOnEn(int courseId, String day, String time) throws DAOException {
        insertMapElement(courseId, day, time, SqlQuery.INSERT_TIMETABLE_ELEMENT_EN);
    }

    /**
     * Inserts a requirement in Russian at the specified course into the database,
     * used {@code SqlQuery.INSERT_REQUIREMENT_ELEMENT_RU} SQL query.
     *
     * @param courseId course id.
     * @param skill    skill title on Russian.
     * @param level    level title on Russian.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public void insertRequirementElementOnRu(int courseId, String skill, String level) throws DAOException {
        insertMapElement(courseId, skill, level, SqlQuery.INSERT_REQUIREMENT_ELEMENT_RU);
    }

    /**
     * Inserts a requirement in English at the specified course into the database,
     * used {@code SqlQuery.INSERT_REQUIREMENT_ELEMENT_EN} SQL query.
     *
     * @param courseId course id.
     * @param skill    skill title on English.
     * @param level    level title on English.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
