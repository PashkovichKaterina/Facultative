package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;

public interface ClassService {
    int getClassesCountByCourse(int courseId) throws ServiceException;

    Map<String, String> getClassDateTimeByCourse(int courseId) throws ServiceException;
}
