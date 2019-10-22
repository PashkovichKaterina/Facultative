package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;

import java.util.Map;

public interface CategoryDAO {
    Map<String, Integer> getAllCategoryWithCourseCountOnEn() throws DAOException;

    Map<String, Integer> getAllCategoryWithCourseCountOnRu() throws DAOException;
}
