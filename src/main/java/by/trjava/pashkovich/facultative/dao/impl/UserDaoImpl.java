package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.UserDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.Teacher;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;
import by.trjava.pashkovich.facultative.dao.exception.CreatorException;
import by.trjava.pashkovich.facultative.dao.creator.UserCreator;

import java.sql.*;
import java.util.*;


public class UserDaoImpl implements UserDAO {
    /**
     * Insert user into database with the specific parameters
     * used {@code SqlQuery.INSERT_USER} SQL query.
     *
     * @param login    user login.
     * @param email    user email.
     * @param password user password.
     * @param role     user role.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public void insertUser(String login, String email, String password, UserRole role) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER)) {
            statement.setString(1, login);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setInt(4, role.getRoleId());
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
     * Insert information about student into database used {@code SqlQuery.INSERT_USER_DETAILS} SQL query.
     *
     * @param userId     user id.
     * @param surname    person surname.
     * @param name       person name.
     * @param patronymic person patronymic.
     * @param phone      person phone.
     * @param date       student date of birth.
     * @param address    student address.
     * @param position   teacher position.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public void insertUserDetails(int userId, String surname, String name, String patronymic, String phone,
                                  String date, String address, String position) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER_DETAILS)) {
            statement.setInt(1, userId);
            statement.setString(2, surname);
            statement.setString(3, name);
            statement.setString(4, patronymic);
            statement.setString(5, phone);
            statement.setString(6, address);
            statement.setString(7, date);
            statement.setString(8, position);
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
     * Update information about student into database user {@code SqlQuery.UPDATE_USER_DETAILS} SQL query.
     *
     * @param userId     user id.
     * @param surname    person surname.
     * @param name       person name.
     * @param patronymic person patronymic.
     * @param phone      person phone.
     * @param date       student date of birth.
     * @param address    student address.
     * @param position   teacher position.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public void updateUserDetails(int userId, String surname, String name, String patronymic, String phone,
                                  String date, String address, String position) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_DETAILS);
            statement.setString(1, surname);
            statement.setString(2, name);
            statement.setString(3, patronymic);
            statement.setString(4, phone);
            statement.setString(5, date);
            statement.setString(6, address);
            statement.setObject(7, position);
            statement.setInt(8, userId);
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
     * Returns user on Russian language with the specific login
     * used {@code SqlQuery.GET_USER_BY_LOGIN_RU} SQL query.
     *
     * @param login user login.
     * @return user with the specific login.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public User getUserByLoginOnRu(String login) throws DAOException {
        return getUserByStringParameter(SqlQuery.GET_USER_BY_LOGIN_RU, login);
    }

    /**
     * Returns user on English language with the specific login
     * used {@code SqlQuery.GET_USER_BY_LOGIN_EN} SQL query.
     *
     * @param login user login.
     * @return user with the specific login.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public User getUserByLoginOnEn(String login) throws DAOException {
        return getUserByStringParameter(SqlQuery.GET_USER_BY_LOGIN_EN, login);
    }

    /**
     * Returns user on Russian with the specific email
     * used {@code SqlQuery.GET_USER_BY_EMAIL_RU} SQL query.
     *
     * @param email user email.
     * @return user with the specific email.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    public User getUserByEmailOnRu(String email) throws DAOException {
        return getUserByStringParameter(SqlQuery.GET_USER_BY_EMAIL_RU, email);
    }

    /**
     * Returns user on English with the specific email
     * used {@code SqlQuery.GET_USER_BY_EMAIL_EN} SQL query.
     *
     * @param email user email.
     * @return user with the specific email.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    public User getUserByEmailOnEn(String email) throws DAOException {
        return getUserByStringParameter(SqlQuery.GET_USER_BY_EMAIL_EN, email);
    }

    /**
     * Returns user with the specific parameter used specific SQL query.
     *
     * @param query     specific query.
     * @param parameter specific parameter.
     * @return user with the specific parameter used specific SQL query.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private User getUserByStringParameter(String query, String parameter) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, parameter);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = UserCreator.create(resultSet);
                }
                return user;
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
     * Returns user on Russian language with the specific id
     * used {@code SqlQuery.GET_USER_BY_ID_RU} SQL query.
     *
     * @param id user id.
     * @return user on Russian language with the specific id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public User getUserByIdOnRu(int id) throws DAOException {
        return getUserById(id, SqlQuery.GET_USER_BY_ID_RU);
    }

    /**
     * Returns user on English language with the specific id
     * used {@code SqlQuery.GET_USER_BY_ID_EN} SQL query.
     *
     * @param id user id.
     * @return user on English language with the specific id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public User getUserByIdOnEn(int id) throws DAOException {
        return getUserById(id, SqlQuery.GET_USER_BY_ID_EN);
    }

    /**
     * Returns user on with the specific id used specific SQL query.
     *
     * @param id    user id.
     * @param query specific query.
     * @return user on with the specific id used specific SQL query.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private User getUserById(int id, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = UserCreator.create(resultSet);
                }
                return user;
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
     * Returns user password with the specific login
     * used {@code SqlQuery.GET_PASSWORD_BY_LOGIN} SQL query.
     *
     * @param login user login
     * @return user password with the specific login.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public String getPasswordByLogin(String login) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        String password = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    password = resultSet.getString(Variable.PASSWORD);
                }
                return password;
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
     * Checks if the database contains user account data with the specified login
     * used {@code SqlQuery.IS_CONTAINS_USER_DETAILS_BY_LOGIN} SQL query.
     *
     * @param login user login.
     * @return {@code true} if the database contains user account data with the specified login,
     * {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public boolean isContainsUserDetailsByLogin(String login) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_USER_DETAILS_BY_LOGIN)) {
            statement.setString(1, login);
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
     * Returns all student from the specific course
     * used {@code SqlQuery.GET_ALL_STUDENT_BY_COURSE} SQL query.
     *
     * @param courseId course id.
     * @return all student from the specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Student> getAllStudentByCourse(int courseId) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Student> students = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_ALL_STUDENT_BY_COURSE)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = (Student) UserCreator.create(resultSet);
                    students.add(student);
                }
                return students;
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
     * Returns all students recorded in the database
     * used {@code SqlQuery.GET_ALL_STUDENT} SQL query.
     *
     * @return all students recorded in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Student> getAllStudent() throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Student> students = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_STUDENT)) {
                while (resultSet.next()) {
                    Student student = (Student) UserCreator.create(resultSet);
                    students.add(student);
                }
                return students;
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
     * Returns all students from the database whose name partially matches the given
     * used {@code SqlQuery.GET_STUDENT_BY_PARTIAL_MATCH} SQL query.
     *
     * @param studentName student name.
     * @return all students from the database whose name partially matches the given.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Student> getStudentByPartialMatch(String studentName) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Student> students = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_STUDENT_BY_PARTIAL_MATCH)) {
            statement.setString(1, "%" + studentName + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = (Student) UserCreator.create(resultSet);
                    students.add(student);
                }
                return students;
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
     * Returns all teachers on Russian language recorded in the database
     * used {@code SqlQuery.GET_ALL_TEACHER_RU} SQL query.
     *
     * @return all teachers on Russian language recorded in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Teacher> getAllTeacherOnRu() throws DAOException {
        return getAllTeacher(SqlQuery.GET_ALL_TEACHER_RU);
    }

    /**
     * Returns all teachers on English language recorded in the database
     * used {@code SqlQuery.GET_ALL_TEACHER_EN} SQL query.
     *
     * @return all teachers on English language recorded in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<Teacher> getAllTeacherOnEn() throws DAOException {
        return getAllTeacher(SqlQuery.GET_ALL_TEACHER_EN);
    }

    /**
     * Returns all teacher recorded in the database by specific query.
     *
     * @param query specific query.
     * @return all teacher recorded in the database by specific query.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private Set<Teacher> getAllTeacher(String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<Teacher> teachers = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Teacher teacher = (Teacher) UserCreator.create(resultSet);
                    teachers.add(teacher);
                }
                return teachers;
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
     * Returns all teachers on Russian language who are stored in the database along
     * with the number of fixed courses used {@code SqlQuery.GET_ALL_TEACHER_WITH_COURSE_COUNT_RU} SQL query.
     *
     * @return all teachers on Russian language who are stored in the database along
     * with the number of fixed courses.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<Teacher, Integer> getAllTeacherWithCourseCountOnRu() throws DAOException {
        return getAllTeacherWithCourseCount(SqlQuery.GET_ALL_TEACHER_WITH_COURSE_COUNT_RU);
    }

    /**
     * Returns all teachers on English language who are stored in the database along
     * with the number of fixed courses used {@code SqlQuery.GET_ALL_TEACHER_WITH_COURSE_COUNT_EN} SQL query.
     *
     * @return all teachers on English language who are stored in the database along
     * with the number of fixed courses.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<Teacher, Integer> getAllTeacherWithCourseCountOnEn() throws DAOException {
        return getAllTeacherWithCourseCount(SqlQuery.GET_ALL_TEACHER_WITH_COURSE_COUNT_EN);
    }

    /**
     * Returns all teachers who are stored in the database along
     * with the number of fixed courses used specific query.
     *
     * @param query specific query.
     * @return all teachers who are stored in the database along
     * with the number of fixed courses used specific query.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private Map<Teacher, Integer> getAllTeacherWithCourseCount(String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Map<Teacher, Integer> teachers = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Teacher teacher = (Teacher) UserCreator.create(resultSet);
                    int courseCount = resultSet.getInt(Variable.COUNT);
                    teachers.put(teacher, courseCount);
                }
                return teachers;
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
     * Returns teachers on Russian language  whose name partially matches the given
     * used {@code SqlQuery.GET_TEACHER_BY_PARTIAL_MATCH_RU} SQL query.
     *
     * @param teacherName teacher name.
     * @return teachers on Russian language  whose name partially matches the given.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatchOnRu(String teacherName) throws DAOException {
        return getTeacherWithCourseCountByPartialMatch(teacherName, SqlQuery.GET_TEACHER_BY_PARTIAL_MATCH_RU);
    }


    /**
     * Returns teachers on English language  whose name partially matches the given
     * used {@code SqlQuery.GET_TEACHER_BY_PARTIAL_MATCH_RE} SQL query.
     *
     * @param teacherName teacher name.
     * @return teachers on English language  whose name partially matches the given.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatchOnEn(String teacherName) throws DAOException {
        return getTeacherWithCourseCountByPartialMatch(teacherName, SqlQuery.GET_TEACHER_BY_PARTIAL_MATCH_EN);
    }

    /**
     * Returns teacher  whose name partially matches the given used specific query.
     *
     * @param teacherName teacher name.
     * @param query       specific query.
     * @return
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatch(String teacherName, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Map<Teacher, Integer> teachers = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + teacherName + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Teacher teacher = (Teacher) UserCreator.create(resultSet);
                    int courseCount = resultSet.getInt(Variable.COUNT);
                    teachers.put(teacher, courseCount);
                }
                return teachers;
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
