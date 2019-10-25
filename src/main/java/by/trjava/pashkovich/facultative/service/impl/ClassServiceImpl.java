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
    @Override
    public int getClassesCountByCourse(int courseId) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        try {
            return classDAO.getClassesCountByCourse(courseId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void addClass(String date, String time, int courseId, int teacherId, HttpServletRequest request) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        try {
            if (ClassValidator.checkDate(date, request) & ClassValidator.checkTime(time, request)) {
                if (CourseValidator.checkCourseId(courseId) && CourseValidator.isCourseFixedForTeacher(courseId, teacherId)) {
                    classDAO.insertClass(courseId, date, time);
                } else {
                    throw new AddClassException("Exception to add class on course = " + courseId + " by teacher = " + teacherId);
                }
            } else {
                throw new AddClassException("Invalid data for add class by teacher with id = " + teacherId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Date> getClassDateTimeByCourse(int courseId) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        Set<Date> classes = new TreeSet<>(Collections.reverseOrder());
        try {
            for (Date classElement : classDAO.getClassDateTimeByCourse(courseId)) {
                classes.add(classElement);
            }
            return classes;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

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
