package by.trjava.pashkovich.facultative.service.validation;

import by.trjava.pashkovich.facultative.dao.CourseDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

public class CourseValidator {
    public static boolean checkCourseId(int id) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return courseDAO.getCourseById(id) != null;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static boolean checkCategoryTitle(String category) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return courseDAO.getCategoryIdByCategoryTitle(category) > 0;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
