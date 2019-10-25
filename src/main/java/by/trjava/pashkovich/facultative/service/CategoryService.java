package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;

public interface CategoryService {
    /**
     * Returns all category title along with the number of classes for each category.
     *
     * @param local language used by the user.
     * @return all category title along with the number of classes for each category.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<String, Integer> getAllCategoryWithCourseCount(String local) throws ServiceException;
}
