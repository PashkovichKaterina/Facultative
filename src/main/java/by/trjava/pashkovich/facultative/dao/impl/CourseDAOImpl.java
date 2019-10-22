package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.CourseDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.*;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;
import by.trjava.pashkovich.facultative.entity.installation.CourseInstaller;
import by.trjava.pashkovich.facultative.entity.installation.CurrentCourseInstaller;
import by.trjava.pashkovich.facultative.entity.installation.UserInstaller;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public Set<Course> getAllCourseOnRu() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Course> courses = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_COURSE_RU)) {
                    while (resultSet.next()) {
                        Course course = new Course();
                        CourseInstaller.install(course, resultSet);
                        courses.add(course);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return courses;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Course> getAllCourseOnEn() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Course> courses = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_COURSE_EN)) {
                    while (resultSet.next()) {
                        Course course = new Course();
                        CourseInstaller.install(course, resultSet);
                        courses.add(course);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return courses;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }


    @Override
    public Course getCourseByIdOnRu(int id) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Course course = null;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_ID_RU)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        course = new Course();
                        CourseInstaller.install(course, resultSet);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return course;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Course getCourseByIdOnEn(int id) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Course course = null;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_ID_EN)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        course = new Course();
                        CourseInstaller.install(course, resultSet);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return course;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Course> getCourseByCategoryOnRu(String category) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Course> courses = new HashSet<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_CATEGORY_RU)) {
                statement.setString(1, category);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Course course = new Course();
                        CourseInstaller.install(course, resultSet);
                        courses.add(course);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return courses;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Course> getCourseByCategoryOnEn(String category) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Course> courses = new HashSet<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_CATEGORY_EN)) {
                statement.setString(1, category);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Course course = new Course();
                        CourseInstaller.install(course, resultSet);
                        courses.add(course);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return courses;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Course> getCourseByTeacherOnRu(int teacherId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Course> courses = new HashSet<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_TEACHER_RU)) {
                statement.setInt(1, teacherId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Course course = new Course();
                        CourseInstaller.install(course, resultSet);
                        courses.add(course);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return courses;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Course> getCourseByTeacherOnEn(int teacherId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Course> courses = new HashSet<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_TEACHER_EN)) {
                statement.setInt(1, teacherId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Course course = new Course();
                        CourseInstaller.install(course, resultSet);
                        courses.add(course);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return courses;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Course> getCourseByPartialMatchTitleOnRu(String title) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Course> courses = new HashSet<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_PARTIAL_MATCH_TITLE_RU)) {
                statement.setString(1, "%" + title + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Course course = new Course();
                        CourseInstaller.install(course, resultSet);
                        courses.add(course);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return courses;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Course> getCourseByPartialMatchTitleOnEn(String title) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Course> courses = new HashSet<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_PARTIAL_MATCH_TITLE_EN)) {
                statement.setString(1, "%" + title + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Course course = new Course();
                        CourseInstaller.install(course, resultSet);
                        courses.add(course);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return courses;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }


    @Override
    public Set<String> getAllCategoryOnRu() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<String> categories = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_CATEGORY_RU)) {
                    while (resultSet.next()) {
                        categories.add(resultSet.getString(Variable.TITLE_RU));
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return categories;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<String> getAllCategoryOnEn() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<String> categories = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_CATEGORY_EN)) {
                    while (resultSet.next()) {
                        categories.add(resultSet.getString(Variable.TITLE_EN));
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return categories;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCourseIdByRuTitle(String title) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            int result = -1;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_ID_BY_RU_TITLE)) {
                statement.setString(1, title);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        result = resultSet.getInt(Variable.COURSE_ID);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCourseIdByEnTitle(String title) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            int result = -1;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_ID_BY_EN_TITLE)) {
                statement.setString(1, title);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        result = resultSet.getInt(Variable.COURSE_ID);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCategoryIdByCategoryTitleOnRu(String categoryTitle) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            int result = -1;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_CATEGORY_BY_TITLE_RU)) {
                statement.setString(1, categoryTitle);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        result = resultSet.getInt(Variable.CATEGORY_ID);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCategoryIdByCategoryTitleOnEn(String categoryTitle) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            int result = -1;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_CATEGORY_BY_TITLE_EN)) {
                statement.setString(1, categoryTitle);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        result = resultSet.getInt(Variable.CATEGORY_ID);
                    }
                }
            } catch (Exception e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, String> getCourseRequirementOnRu(int courseId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<String, String> require = new HashMap<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_REQUIREMENT_RU)) {
                statement.setInt(1, courseId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        require.put(resultSet.getString(Variable.SKILL), resultSet.getString(Variable.LEVEL));
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return require;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, String> getCourseRequirementOnEn(int courseId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<String, String> require = new HashMap<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_REQUIREMENT_EN)) {
                statement.setInt(1, courseId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        require.put(resultSet.getString(Variable.SKILL), resultSet.getString(Variable.LEVEL));
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return require;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Date> getCourseTimetableOnRu(int courseId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<String, Date> timeTable = new HashMap<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_TIMETABLE_RU)) {
                statement.setInt(1, courseId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        timeTable.put(resultSet.getString(Variable.DAY), resultSet.getTime(Variable.TIME));
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return timeTable;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Date> getCourseTimetableOnEn(int courseId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<String, Date> timeTable = new HashMap<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_TIMETABLE_EN)) {
                statement.setInt(1, courseId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        timeTable.put(resultSet.getString(Variable.DAY), resultSet.getTime(Variable.TIME));
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return timeTable;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }


    @Override
    public Set<CurrentCourse> getStudentCurrentCourseOnRu(int studentId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<CurrentCourse> currentCourses = new HashSet<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_STUDENT_CURRENT_COURSE_RU)) {
                statement.setInt(1, studentId);
                statement.setInt(2, studentId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        CurrentCourse currentCourse = new CurrentCourse();
                        CurrentCourseInstaller.install(currentCourse, resultSet);
                        currentCourses.add(currentCourse);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return currentCourses;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<CurrentCourse> getStudentCurrentCourseOnEn(int studentId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<CurrentCourse> currentCourses = new HashSet<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_STUDENT_CURRENT_COURSE_EN)) {
                statement.setInt(1, studentId);
                statement.setInt(2, studentId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        CurrentCourse currentCourse = new CurrentCourse();
                        CurrentCourseInstaller.install(currentCourse, resultSet);
                        currentCourses.add(currentCourse);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return currentCourses;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean isCourseFixedForTeacher(int courseId, int teacherId) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            boolean result;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_COURSE_FIXED_FOR_TEACHER)) {
                statement.setInt(1, courseId);
                statement.setInt(2, teacherId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    result = resultSet.next();
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return result;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public void insertCourse(String titleRu, String titleEn, int teacherId, int classCount, int categoryId,
                             boolean availability, String descriptionRu, String descriptionEn) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
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
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public void insertTimetableElementOnRu(int courseId, String day, String time) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TIMETABLE_ELEMENT_RU)) {
                statement.setInt(1, courseId);
                statement.setString(2, day);
                statement.setString(3, time);
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
    public void insertTimetableElementOnEn(int courseId, String day, String time) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TIMETABLE_ELEMENT_EN)) {
                statement.setInt(1, courseId);
                statement.setString(2, day);
                statement.setString(3, time);
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
    public void insertRequirementElementOnRu(int courseId, String skill, String level) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_REQUIREMENT_ELEMENT_RU)) {
                statement.setInt(1, courseId);
                statement.setString(2, skill);
                statement.setString(3, level);
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
    public void insertRequirementElementOnEn(int courseId, String skill, String level) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_REQUIREMENT_ELEMENT_EN)) {
                statement.setInt(1, courseId);
                statement.setString(2, skill);
                statement.setString(3, level);
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
