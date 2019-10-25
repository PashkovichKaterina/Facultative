package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.Teacher;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

public interface UserService {
    /**
     * Logs in user account.
     *
     * @param login    user login.
     * @param password user password
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void login(String login, String password) throws ServiceException;

    /**
     * Performs user registration in the system.
     *
     * <p>Registration is possible if the login and email are entered correctly and are unique.</p>
     *
     * @param request the {@code HttpServletRequest} object.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void registration(HttpServletRequest request) throws ServiceException;

    /**
     * Edit student account data.
     *
     * <p>It possible if the surname, name, patronymic and phone are not empty and matches for the format.</p>
     *
     * @param request the {@code HttpServletRequest} object.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void editStudent(HttpServletRequest request) throws ServiceException;

    /**
     * Returns user with specific login.
     *
     * @param login user login.
     * @param local language used by the user.
     * @return user with specific login.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    User getUserByLogin(String login, String local) throws ServiceException;

    /**
     * Returns user with specific id.
     *
     * @param userId user id.
     * @param local  language used by the user.
     * @return user with specific id.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    User getUserById(int userId, String local) throws ServiceException;

    /**
     * Return all student from the database sorted by {@code PersonComparator<>}.
     *
     * @return all student from the database in sorted order.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<Student> getAllStudent() throws ServiceException;

    /**
     * Returns all students whose name partially matches the entered.
     *
     * @param studentName student name.
     * @return all students whose name partially matches the entered.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<Student> getStudentByPartialMatch(String studentName) throws ServiceException;

    /**
     * Return all teacher from the database sorted by {@code PersonComparator<>}.
     *
     * @return all teacher from the database in sorted order.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<Teacher> getAllTeacher(String local) throws ServiceException;

    /**
     * Returns teachers with the number of fixed courses for each sotred by {@code PersonComparator<>}.
     *
     * @param local language used by the user.
     * @return teachers with the number of fixed courses for each in sorted order.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<Teacher, Integer> getAllTeacherWithCourseCount(String local) throws ServiceException;

    /**
     * Returns teachers with the number of fixed courses for each
     * whose name partially matches the entered order by {@code PersonComparator<>}.
     *
     * @param teacherName teacher name
     * @param local       language used by the user.
     * @return teachers with the number of fixed courses for each
     * whose name partially matches the entered in sorted order.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatch(String teacherName, String local) throws ServiceException;
}
