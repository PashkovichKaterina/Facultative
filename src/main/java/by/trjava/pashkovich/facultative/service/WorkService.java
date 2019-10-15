package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Set;

public interface WorkService {
    Set<String> getWorkTitleByCourse(int courseId) throws ServiceException;
}
