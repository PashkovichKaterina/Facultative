package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.CurrentCourse;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface CourseService {
    Course getCourseById(int id) throws ServiceException;

    Set<Course> getAllCourse() throws ServiceException;

    Set<String> getAllCategory() throws ServiceException;

    Map<String, String> getCourseRequirement(int id) throws ServiceException;

    Map<String, Date> getCourseTimetable(int id) throws ServiceException;

    Set<Course> searchCourse(String courseTitle, String category) throws ServiceException;

    Set<CurrentCourse> getStudentCurrentCourse(int studentId) throws ServiceException;

    Set<Course> getCourseByTeacher(int teacherId) throws ServiceException;
}
