package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.WorkDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.WorkService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Set;

public class WorkServiceImpl implements WorkService {
    @Override
    public Set<String> getWorkTitleByCourse(int courseId) throws ServiceException {
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        try {
            return workDAO.getWorkTitleByCourse(courseId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
