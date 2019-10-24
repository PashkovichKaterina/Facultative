package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.Teacher;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

public interface UserService {
    void login(String login, String password) throws ServiceException;

    void registration(HttpServletRequest request) throws ServiceException;

    void editStudent(HttpServletRequest request) throws ServiceException;

    User getUserByLogin(String login, String local) throws ServiceException;

    User getUserById(int userId, String local) throws ServiceException;

    Set<Student> getAllStudent() throws ServiceException;

    Set<Student> getStudentByPartialMatch(String studentName) throws ServiceException;

    Set<Teacher> getAllTeacher(String local) throws ServiceException;

    Map<Teacher, Integer> getAllTeacherWithCourseCount(String local) throws ServiceException;

    Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatch(String teacherName, String local) throws ServiceException;
}
