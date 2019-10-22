package by.trjava.pashkovich.facultative.service.validation;

import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.WorkDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

public class WorkValidator {
    public static boolean isWorkTitleContained(String workTitle, int courseId) throws ServiceException {
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        try {
            return workDAO.getWorkId(courseId, workTitle) > 0;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static boolean isWorkAvailableForTeacher(int workId, int teacherId) throws ServiceException {
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        try {
            return workDAO.isWorkAvailableForTeacher(workId, teacherId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static boolean isWorkAvailableForStudent(int workId, int studentId) throws ServiceException {
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        try {
            return workDAO.isWorkAvailableForStudent(workId, studentId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
