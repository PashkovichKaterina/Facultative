package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.CategoryDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.CategoryService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;

import java.util.Map;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public Map<String, Integer> getAllCategoryWithCourseCount(String local) throws ServiceException {
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? categoryDAO.getAllCategoryWithCourseCountOnEn()
                    : categoryDAO.getAllCategoryWithCourseCountOnRu();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
