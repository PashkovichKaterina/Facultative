package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.ClassDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.ClassService;
import by.trjava.pashkovich.facultative.service.exception.AddClassException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;
import by.trjava.pashkovich.facultative.service.validation.ClassValidator;
import by.trjava.pashkovich.facultative.service.validation.CourseValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ClassServiceImpl implements ClassService {
    /**
     * Returns the number of classes at a specific course.
     *
     * @param courseId course id.
     * @return the number of classes at a specific course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public int getClassesCountByCourse(int courseId) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        if (!CourseValidator.checkCourseId(courseId)) {
            throw new ServiceException("Invalid course id data for get the number of classes");
        }
        try {
            return classDAO.getClassesCountByCourse(courseId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Adds a class with the specified date and time to the specified course if possible.
     *
     * <p>You can add a lesson to the course if the course is fixed for the specific teacher
     * and no classes were held on the specified day.</p>
     *
     * @param date      class date.
     * @param time      class time.
     * @param courseId  course id.
     * @param teacherId teacher id.
     * @param request   the {@code HttpServletRequest} object.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public void addClass(String date, String time, int courseId, int teacherId, HttpServletRequest request) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        try {
            if (CourseValidator.checkCourseId(courseId) && CourseValidator.isCourseFixedForTeacher(courseId, teacherId)) {
                if (ClassValidator.checkDate(date, courseId, request) & ClassValidator.checkTime(time, request)) {
                    classDAO.insertClass(courseId, date, time);
                } else {
                    throw new AddClassException("Invalid data for add class by teacher with id = " + teacherId);
                }
            } else {
                throw new AddClassException("Exception to add class on course = " + courseId + " by teacher = " + teacherId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns the date and time of all classes that were held at a specific course.
     *
     * @param courseId course id.
     * @return the date and time of all classes that were held at a specific course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Set<Date> getClassDateTimeByCourse(int courseId) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        Set<Date> classes = new TreeSet<>(Collections.reverseOrder());
        if (!CourseValidator.checkCourseId(courseId)) {
            throw new ServiceException("Invalid course id data for get classes date time by course " + courseId);
        }
        try {
            classes.addAll(classDAO.getClassDateTimeByCourse(courseId));
            return classes;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns a set of all days of the week from the database on which classes can be held.
     *
     * @param local language used by the user.
     * @return a set of all days of the week from the database on which classes can be held.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Set<String> getAllDays(String local) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? classDAO.getAllDaysOnEn()
                    : classDAO.getAllDaysOnRu();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
