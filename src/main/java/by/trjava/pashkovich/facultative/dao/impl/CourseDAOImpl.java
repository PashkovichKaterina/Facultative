package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.CourseDAO;
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
    public Set<Course> getAllCourse() throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Set<Course> courses = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_COURSE)) {
                while (resultSet.next()) {
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    courses.add(course);
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return courses;
    }


    @Override
    public Course getCourseById(int id) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Course course = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    course = new Course();
                    CourseInstaller.install(course, resultSet);
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return course;
    }

    @Override
    public Set<Course> getCourseByCategory(String category) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Set<Course> courses = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_CATEGORY)) {
            statement.setString(1, category);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    courses.add(course);
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        System.out.println(courses);
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return courses;
    }

    @Override
    public Set<Course> getCourseByTeacher(int teacherId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Set<Course> courses = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_TEACHER)) {
            statement.setInt(1, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    courses.add(course);
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        System.out.println(courses);
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return courses;
    }

    @Override
    public Set<Course> getCourseByPartialMatchTitle(String title) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Set<Course> courses = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_BY_PARTIAL_MATCH_TITLE)) {
            statement.setString(1, "%" + title + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    courses.add(course);
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return courses;
    }


    @Override
    public Set<String> getAllCategory() throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Set<String> categories = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_CATEGORY)) {
                while (resultSet.next()) {
                    categories.add(resultSet.getString(Variable.TITLE));
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return categories;
    }

    @Override
    public String getCategoryById(int categoryId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        String category = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_CATEGORY_BY_ID)) {
            statement.setInt(1, categoryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    category = resultSet.getString(Variable.CATEGORY_TITLE);
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return category;
    }

    @Override
    public int getCategoryIdByCategoryTitle(String categoryTitle) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        int result = -1;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_CATEGORY_BY_TITLE)) {
            statement.setString(1, categoryTitle);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt(Variable.CATEGORY_ID);
                }
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return result;
    }

    @Override
    public Map<String, String> getCourseRequirement(int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Map<String, String> require = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_REQUIREMENT)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    require.put(resultSet.getString(Variable.SKILL), resultSet.getString(Variable.LEVEL));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return require;
    }

    @Override
    public Map<String, Date> getCourseTimetable(int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Map<String, Date> timeTable = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_COURSE_TIMETABLE)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    timeTable.put(resultSet.getString(Variable.DAY), resultSet.getTime(Variable.TIME));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return timeTable;
    }


    @Override
    public Set<CurrentCourse> getStudentCurrentCourse(int studentId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Set<CurrentCourse> currentCourses = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_STUDENT_CURRENT_COURSE)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CurrentCourse currentCourse = new CurrentCourse();
                    CurrentCourseInstaller.install(currentCourse, resultSet);
                    currentCourses.add(currentCourse);
                }
            }
        } catch (SQLException | InstallerException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return currentCourses;
    }

    @Override
    public Set<Student> getAllStudentByCourse(int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Set<Student> students = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_ALL_STUDENT_BY_COURSE)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = new Student();
                    UserInstaller.install(student, resultSet);
                    students.add(student);
                }
            }
        } catch (SQLException | InstallerException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return students;
    }


}
