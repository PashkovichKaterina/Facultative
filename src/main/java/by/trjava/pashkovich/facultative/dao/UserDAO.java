package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.Teacher;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;

import java.util.Map;
import java.util.Set;


public interface UserDAO {
    /**
     * Insert user into database with the specific parameters.
     *
     * @param login    user login.
     * @param email    user email.
     * @param password user password.
     * @param role     user role.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertUser(String login, String email, String password, UserRole role) throws DAOException;

    /**
     * Insert information about student into database.
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
    void insertUserDetails(int userId, String surname, String name, String patronymic, String phone,
                           String date, String address, String position) throws DAOException;

    /**
     * Update information about student into database.
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
    void updateUserDetails(int userId, String surname, String name, String patronymic, String phone,
                           String date, String address, String position) throws DAOException;

    /**
     * Returns user on Russian language with the specific id.
     *
     * @param id user id.
     * @return user on Russian language with the specific id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    User getUserByIdOnRu(int id) throws DAOException;

    /**
     * Returns user on English language with the specific id.
     *
     * @param id user id.
     * @return user on Russian language with the specific id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    User getUserByIdOnEn(int id) throws DAOException;

    /**
     * Returns user on Russian language with the specific login.
     *
     * @param login user login.
     * @return user with the specific login.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    User getUserByLoginOnRu(String login) throws DAOException;

    /**
     * Returns user on English language with the specific login.
     *
     * @param login user login.
     * @return user with the specific login.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    User getUserByLoginOnEn(String login) throws DAOException;

    /**
     * Returns user on Russian with the specific email.
     *
     * @param email user email.
     * @return user with the specific email.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    User getUserByEmailOnRu(String email) throws DAOException;

    /**
     * Returns user on English with the specific email.
     *
     * @param email user email.
     * @return user with the specific email.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    User getUserByEmailOnEn(String email) throws DAOException;

    /**
     * Returns user password with the specific login.
     *
     * @param login user login
     * @return user password with the specific login.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    String getPasswordByLogin(String login) throws DAOException;

    /**
     * Checks if the database contains user account data with the specified login.
     *
     * @param login user login.
     * @return {@code true} if the database contains user account data with the specified login,
     * {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    boolean isContainsUserDetailsByLogin(String login) throws DAOException;

    /**
     * Returns all student from the specific course.
     *
     * @param courseId course id.
     * @return all student from the specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Student> getAllStudentByCourse(int courseId) throws DAOException;

    /**
     * Returns all students recorded in the database.
     *
     * @return all students recorded in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Student> getAllStudent() throws DAOException;

    /**
     * Returns all students from the database whose name partially matches the given.
     *
     * @param studentName student name.
     * @return all students from the database whose name partially matches the given.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Student> getStudentByPartialMatch(String studentName) throws DAOException;

    /**
     * Returns all teachers on Russian language recorded in the database.
     *
     * @return all teachers on Russian language recorded in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Teacher> getAllTeacherOnRu() throws DAOException;

    /**
     * Returns all teachers on English language recorded in the database.
     *
     * @return all teachers on English language recorded in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Teacher> getAllTeacherOnEn() throws DAOException;

    /**
     * Returns all teachers on Russian language who are stored in the database along
     * with the number of fixed courses.
     *
     * @return all teachers on Russian language who are stored in the database along
     * with the number of fixed courses.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<Teacher, Integer> getAllTeacherWithCourseCountOnRu() throws DAOException;

    /**
     * Returns all teachers on English language who are stored in the database along
     * with the number of fixed courses.
     *
     * @return all teachers on English language who are stored in the database along
     * with the number of fixed courses.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<Teacher, Integer> getAllTeacherWithCourseCountOnEn() throws DAOException;

    /**
     * Returns teachers on Russian language  whose name partially matches the given.
     *
     * @param teacherName teacher name.
     * @return teachers on Russian language  whose name partially matches the given.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatchOnRu(String teacherName) throws DAOException;

    /**
     * Returns teachers on English language  whose name partially matches the given.
     *
     * @param teacherName teacher name.
     * @return teachers on English language  whose name partially matches the given.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatchOnEn(String teacherName) throws DAOException;
}
