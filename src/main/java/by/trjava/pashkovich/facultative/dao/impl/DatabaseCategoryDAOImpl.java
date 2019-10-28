package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.CategoryDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DatabaseCategoryDAOImpl implements CategoryDAO {
    /**
     * Returns all categories on Russian language with the number of classes for each
     * used {@code SqlQuery.GET_ALL_CATEGORY_WITH_COURSE_COUNT_RU} SQL query.
     *
     * @return all categories on Russian language with the number of classes for each.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<String, Integer> getAllCategoryWithCourseCountOnRu() throws DAOException {
        return getAllCategoryWithCourseCount(SqlQuery.GET_ALL_CATEGORY_WITH_COURSE_COUNT_RU);
    }

    /**
     * Returns all categories on English language with the number of classes for each
     * used {@code SqlQuery.GET_ALL_CATEGORY_WITH_COURSE_COUNT_EN} SQL query.
     *
     * @return all categories on English language with the number of classes for each.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<String, Integer> getAllCategoryWithCourseCountOnEn() throws DAOException {
        return getAllCategoryWithCourseCount(SqlQuery.GET_ALL_CATEGORY_WITH_COURSE_COUNT_EN);
    }

    /**
     * Returns all categories with the number of classes for each used specific query.
     *
     * @param query specific query.
     * @return all categories with the number of classes for each used specific query.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    public Map<String, Integer> getAllCategoryWithCourseCount(String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Map<String, Integer> categories = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String skillTitle = resultSet.getString(Variable.TITLE);
                    int count = resultSet.getInt(Variable.COUNT);
                    categories.put(skillTitle, count);
                }
                return categories;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Returns all categories on Russian language are stored in database,
     * used {@code SqlQuery.GET_ALL_CATEGORY_RU} SQL query.
     *
     * @return all categories on Russian language are stored in database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<String> getAllCategoryOnRu() throws DAOException {
        return getAllCategory(SqlQuery.GET_ALL_CATEGORY_RU);
    }

    /**
     * Returns all categories on English language are stored in database,
     * used {@code SqlQuery.GET_ALL_CATEGORY_EN} SQL query.
     *
     * @return all categories on English language are stored in database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<String> getAllCategoryOnEn() throws DAOException {
        return getAllCategory(SqlQuery.GET_ALL_CATEGORY_EN);
    }

    /**
     * Returns all categories are stored in database, used specific query.
     *
     * @param query specific query.
     * @return all categories are stored in database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private Set<String> getAllCategory(String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<String> categories = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    categories.add(resultSet.getString(Variable.TITLE));
                }
                return categories;
            }
        } catch (Exception e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Returns category id with specific category title,
     * used {@code SqlQuery.GET_CATEGORY_BY_TITLE_RU} SQL query.
     *
     * @param categoryTitle category title on Russian.
     * @return category id with specific category title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public int getCategoryIdByCategoryTitleOnRu(String categoryTitle) throws DAOException {
        return getCategoryIdByCategoryTitle(categoryTitle, SqlQuery.GET_CATEGORY_BY_TITLE_RU);
    }

    /**
     * Returns category id with specific category title,
     * used {@code SqlQuery.GET_CATEGORY_BY_TITLE_EN} SQL query.
     *
     * @param categoryTitle category title on English.
     * @return category id with specific category title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public int getCategoryIdByCategoryTitleOnEn(String categoryTitle) throws DAOException {
        return getCategoryIdByCategoryTitle(categoryTitle, SqlQuery.GET_CATEGORY_BY_TITLE_EN);
    }

    /**
     * Returns category id with specific category title, used specific query.
     *
     * @param categoryTitle category title on Russian.
     * @param query         specific query.
     * @return category id with specific category title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private int getCategoryIdByCategoryTitle(String categoryTitle, String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        int result = -1;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categoryTitle);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt(Variable.CATEGORY_ID);
                }
                return result;
            }
        } catch (Exception e) {
            throw new DAOException("Exception in SQL: " + e.getMessage(), e);
        } finally {
            try {
                BaseConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
            }
        }
    }
}
