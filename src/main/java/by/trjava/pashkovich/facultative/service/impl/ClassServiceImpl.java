package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.ClassDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.ClassService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ClassServiceImpl implements ClassService {
    @Override
    public int getClassesCountByCourse(int courseId) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        try {
            return classDAO.getClassesCountByCourse(courseId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<String, String> getClassDateTimeByCourse(int courseId) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        try {
            return new TreeMap<>(classDAO.getClassDateTimeByCourse(courseId));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
