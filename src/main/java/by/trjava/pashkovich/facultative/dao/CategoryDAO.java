package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;

import java.util.Map;
import java.util.Set;

public interface CategoryDAO {
    /**
     * Returns all categories on Russian language with the number of classes for each.
     *
     * @return all categories on Russian language with the number of classes for each.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<String, Integer> getAllCategoryWithCourseCountOnEn() throws DAOException;

    /**
     * Returns all categories on English language with the number of classes for each.
     *
     * @return all categories on English language with the number of classes for each.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<String, Integer> getAllCategoryWithCourseCountOnRu() throws DAOException;

    /**
     * Returns all categories on Russian language are stored in database.
     *
     * @return all categories on Russian language are stored in database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<String> getAllCategoryOnRu() throws DAOException;

    /**
     * Returns all categories on English language are stored in database.
     *
     * @return all categories on English language are stored in database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<String> getAllCategoryOnEn() throws DAOException;

    /**
     * Returns category id with specific category title.
     *
     * @param categoryTitle category title on Russian.
     * @return category id with specific category title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    int getCategoryIdByCategoryTitleOnRu(String categoryTitle) throws DAOException;

    /**
     * Returns category id with specific category title.
     *
     * @param categoryTitle category title on English.
     * @return category id with specific category title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    int getCategoryIdByCategoryTitleOnEn(String categoryTitle) throws DAOException;
}
