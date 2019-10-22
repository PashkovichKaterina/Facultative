package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.UserDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.CustomFormatForDate;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.Teacher;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;
import by.trjava.pashkovich.facultative.entity.factory.UserFactory;
import by.trjava.pashkovich.facultative.entity.installation.UserInstaller;

import java.sql.*;
import java.util.*;
import java.util.Date;


public class UserDaoImpl implements UserDAO {

    @Override
    public void insertUser(String login, String email, String password, UserRole role) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER)) {
                statement.setString(1, login);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.setInt(4, role.getRoleId());
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
    public void insertUserDetails(int userId, String surname, String name, String patronymic, String phone,
                                  String date, String address, String position) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
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
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateUserDetails(int userId, String surname, String name, String patronymic, String phone,
                                  String date, String address, String position) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
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
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public User getUserByLoginOnRu(String login) throws DAOException {
        return getUserByStringParameter(SqlQuery.GET_USER_BY_LOGIN_RU, login);
    }

    @Override
    public User getUserByLoginOnEn(String login) throws DAOException {
        return getUserByStringParameter(SqlQuery.GET_USER_BY_LOGIN_EN, login);
    }

    public User getUserByEmailOnRu(String email) throws DAOException {
        return getUserByStringParameter(SqlQuery.GET_USER_BY_EMAIL_RU, email);
    }

    public User getUserByEmailOnEn(String email) throws DAOException {
        return getUserByStringParameter(SqlQuery.GET_USER_BY_EMAIL_EN, email);
    }

    private User getUserByStringParameter(String query, String parameter) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            User user = null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, parameter);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        UserRole role = UserRole.getRole(resultSet.getInt(Variable.ROLE_ID));
                        user = UserFactory.createUser(role);
                        UserInstaller.install(user, resultSet);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public User getUserByIdOnRu(int id) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            User user = null;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_USER_BY_ID_RU)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        UserRole role = UserRole.getRole(resultSet.getInt(Variable.ROLE_ID));
                        user = UserFactory.createUser(role);
                        UserInstaller.install(user, resultSet);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public User getUserByIdOnEn(int id) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            User user = null;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_USER_BY_ID_EN)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        UserRole role = UserRole.getRole(resultSet.getInt(Variable.ROLE_ID));
                        user = UserFactory.createUser(role);
                        UserInstaller.install(user, resultSet);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public String getPasswordByLogin(String login) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            String password = null;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_PASSWORD_BY_LOGIN)) {
                statement.setString(1, login);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        password = resultSet.getString(Variable.PASSWORD);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return password;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }


    @Override
    public boolean isContainsUserDetailsByLogin(String login) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            boolean result = false;
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_USER_DETAILS_BY_LOGIN)) {
                statement.setString(1, login);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        result = true;
                    }
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
    public Set<Student> getAllStudentByCourse(int courseId) throws DAOException {
        try {
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
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return students;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Student> getAllStudent() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Student> students = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_STUDENT)) {
                    while (resultSet.next()) {
                        Student student = new Student();
                        UserInstaller.install(student, resultSet);
                        students.add(student);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return students;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Student> getStudentByPartialMatch(String studentName) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Student> students = new HashSet<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_STUDENT_BY_PARTIAL_MATCH)) {
                statement.setString(1, "%" + studentName + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Student student = new Student();
                        UserInstaller.install(student, resultSet);
                        students.add(student);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return students;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Teacher> getAllTeacherOnRu() throws DAOException{
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Teacher> teachers = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_TEACHER_RU)) {
                    while (resultSet.next()) {
                        Teacher teacher = new Teacher();
                        UserInstaller.install(teacher, resultSet);
                        teachers.add(teacher);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return teachers;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Teacher> getAllTeacherOnEn() throws DAOException{
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<Teacher> teachers = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_TEACHER_EN)) {
                    while (resultSet.next()) {
                        Teacher teacher = new Teacher();
                        UserInstaller.install(teacher, resultSet);
                        teachers.add(teacher);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return teachers;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }
    @Override
    public Map<Teacher, Integer> getAllTeacherWithCourseCountOnRu() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<Teacher, Integer> teachers = new HashMap<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_TEACHER_WITH_COURSE_COUNT_RU)) {
                    while (resultSet.next()) {
                        Teacher teacher = new Teacher();
                        UserInstaller.install(teacher, resultSet);
                        int courseCount = resultSet.getInt(Variable.COUNT);
                        teachers.put(teacher, courseCount);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return teachers;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<Teacher, Integer> getAllTeacherWithCourseCountOnEn() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<Teacher, Integer> teachers = new HashMap<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_TEACHER_WITH_COURSE_COUNT_EN)) {
                    while (resultSet.next()) {
                        Teacher teacher = new Teacher();
                        UserInstaller.install(teacher, resultSet);
                        int courseCount = resultSet.getInt(Variable.COUNT);
                        teachers.put(teacher, courseCount);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return teachers;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }


    @Override
    public Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatchOnRu(String teacherName) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<Teacher, Integer> teachers = new HashMap<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_TEACHER_BY_PARTIAL_MATCH_RU)) {
                statement.setString(1, "%" + teacherName + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Teacher teacher = new Teacher();
                        UserInstaller.install(teacher, resultSet);
                        int courseCount = resultSet.getInt(Variable.COUNT);
                        teachers.put(teacher, courseCount);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return teachers;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatchOnEn(String teacherName) throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<Teacher, Integer> teachers = new HashMap<>();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_TEACHER_BY_PARTIAL_MATCH_EN)) {
                statement.setString(1, "%" + teacherName + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Teacher teacher = new Teacher();
                        UserInstaller.install(teacher, resultSet);
                        int courseCount = resultSet.getInt(Variable.COUNT);
                        teachers.put(teacher, courseCount);
                    }
                }
            } catch (SQLException | InstallerException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return teachers;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }
}
