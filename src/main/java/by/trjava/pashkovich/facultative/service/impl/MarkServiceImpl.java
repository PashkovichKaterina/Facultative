package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.MarkDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.MarkService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;

public class MarkServiceImpl implements MarkService {
    @Override
    public int getStudentAverageMarkByCourse(int studentId, int courseId) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        try {
            return markDAO.getStudentAverageMarkByCourse(studentId, courseId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<String, Integer> getStudentMarkWithWorkTitleByCourse(int studentId, int courseId) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        try {
            return markDAO.getStudentMarkWithWorkTitleByCourse(studentId, courseId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
