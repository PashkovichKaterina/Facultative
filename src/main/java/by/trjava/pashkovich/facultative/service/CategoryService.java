package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;

public interface CategoryService {
    Map<String, Integer> getAllCategoryWithCourseCount(String local) throws ServiceException;
}
