package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.CourseDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.CurrentCourse;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.comparator.CurrentCourseComparatorByDate;
import by.trjava.pashkovich.facultative.service.exception.NoSuchCategoryException;
import by.trjava.pashkovich.facultative.service.exception.NoSuchCourseException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.validation.CourseValidator;
import by.trjava.pashkovich.facultative.service.validation.FieldValidator;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CourseServiceImpl implements CourseService {
    @Override
    public Course getCourseById(int id) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        if (!CourseValidator.checkCourseId(id)) {
            throw new NoSuchCourseException();
        }
        try {
            return courseDAO.getCourseById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public Set<Course> getAllCourse() throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return courseDAO.getAllCourse();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Set<String> getAllCategory() throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return new TreeSet<>(courseDAO.getAllCategory());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<String, String> getCourseRequirement(int id) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        if (!CourseValidator.checkCourseId(id)) {
            throw new NoSuchCourseException();
        }
        try {
            return courseDAO.getCourseRequirement(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<String, Date> getCourseTimetable(int id) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        if (!CourseValidator.checkCourseId(id)) {
            throw new NoSuchCourseException();
        }
        try {
            return courseDAO.getCourseTimetable(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Set<Course> searchCourse(String courseTitle, String category) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        Set<Course> courses;
        if (FieldValidator.isEmpty(courseTitle) && FieldValidator.isEmpty(category)) {
            return getAllCourse();
        }
        if (!FieldValidator.isEmpty(courseTitle)) {
            try {
                courses = courseDAO.getCourseByPartialMatchTitle(courseTitle);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            if (!CourseValidator.checkCategoryTitle(category)) {
                throw new NoSuchCategoryException();
            }
            try {
                courses = courseDAO.getCourseByCategory(category);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        if (FieldValidator.isEmpty(courses)) {
            throw new NoSuchCourseException();
        }
        return courses;
    }

    @Override
    public Set<CurrentCourse> getStudentCurrentCourse(int studentId) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        Set<CurrentCourse> courses = new TreeSet<>(new CurrentCourseComparatorByDate());
        try {
            for (CurrentCourse c : courseDAO.getStudentCurrentCourse(studentId)) {
                courses.add(c);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return courses;
    }

    @Override
    public Set<Course> getCourseByTeacher(int teacherId) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return courseDAO.getCourseByTeacher(teacherId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
