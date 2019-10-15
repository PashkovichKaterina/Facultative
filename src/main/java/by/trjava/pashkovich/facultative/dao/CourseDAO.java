package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface CourseDAO {

    Set<Course> getAllCourse() throws DAOException;

    Set<Course> getCourseByCategory(String category) throws DAOException;

    Course getCourseById(int courseId) throws DAOException;

    Set<Course> getCourseByPartialMatchTitle(String courseTitle) throws DAOException;

    Set<Course> getCourseByTeacher(int teacherId) throws DAOException;

    Set<String> getAllCategory() throws DAOException;

    String getCategoryById(int categoryId) throws DAOException;

    int getCategoryIdByCategoryTitle(String categoryTitle) throws DAOException;

    Map<String, String> getCourseRequirement(int courseId) throws DAOException;

    Map<String, Date> getCourseTimetable(int courseId) throws DAOException;

    Set<CurrentCourse> getStudentCurrentCourse(int studentId) throws DAOException;

    Set<Student> getAllStudentByCourse(int courseId) throws DAOException;
}
