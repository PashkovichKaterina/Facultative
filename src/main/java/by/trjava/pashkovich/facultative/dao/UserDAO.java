package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.Teacher;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;

import java.util.Date;
import java.util.Map;
import java.util.Set;


public interface UserDAO {

    void insertUser(String login, String email, String password, UserRole role) throws DAOException;


    void insertUserDetails(int userId, String surname, String name, String patronymic, String phone,
                           String date, String address, String position) throws DAOException;

    void updateUserDetails(int userId, String surname, String name, String patronymic, String phone,
                           String date, String address, String position) throws DAOException;

    User getUserByIdOnRu(int id) throws DAOException;

    User getUserByIdOnEn(int id) throws DAOException;

    User getUserByLoginOnRu(String login) throws DAOException;

    User getUserByLoginOnEn(String login) throws DAOException;

    User getUserByEmailOnRu(String email) throws DAOException;

    User getUserByEmailOnEn(String email) throws DAOException;

    String getPasswordByLogin(String login) throws DAOException;

    boolean isContainsUserDetailsByLogin(String login) throws DAOException;

    Set<Student> getAllStudentByCourse(int courseId) throws DAOException;

    Set<Student> getAllStudent() throws DAOException;

    Set<Student> getStudentByPartialMatch(String studentName) throws DAOException;

    Set<Teacher> getAllTeacherOnRu() throws DAOException;

    Set<Teacher> getAllTeacherOnEn() throws DAOException;

    Map<Teacher, Integer> getAllTeacherWithCourseCountOnRu() throws DAOException;

    Map<Teacher, Integer> getAllTeacherWithCourseCountOnEn() throws DAOException;

    Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatchOnRu(String teacherName) throws DAOException;

    Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatchOnEn(String teacherName) throws DAOException;
}
