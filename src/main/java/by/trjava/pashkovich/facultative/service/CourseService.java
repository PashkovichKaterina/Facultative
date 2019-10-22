package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.CurrentCourse;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface CourseService {
    Course getCourseById(int id, String local) throws ServiceException;

    Set<Course> getAllCourse(String local) throws ServiceException;

    Set<String> getAllCategory(String local) throws ServiceException;

    Map<String, String> getCourseRequirement(int id, String local) throws ServiceException;

    Map<String, Date> getCourseTimetable(int id, String local) throws ServiceException;

    Set<Course> searchCourse(String courseTitle, String category, String local) throws ServiceException;

    Set<CurrentCourse> getStudentCurrentCourse(int studentId, String local) throws ServiceException;

    Map<Course, String> getAllCourseWithStatus(String local) throws ServiceException;

    Map<Course, String> getCourseWithStatusByTeacher(int teacherId, String local) throws ServiceException;

    Set<Student> getAllStudentByCourse(int courseId) throws ServiceException;

    int getCategoryIdByCategoryTitle(String categoryTitle, String local) throws ServiceException;

    void insertCourse(HttpServletRequest request) throws ServiceException;
}
