package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.CategoryDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public Map<String, Integer> getAllCategoryWithCourseCountOnRu() throws DAOException {
        return getAllCategoryWithCourseCount(SqlQuery.GET_ALL_CATEGORY_WITH_COURSE_COUNT_RU);
    }

    @Override
    public Map<String, Integer> getAllCategoryWithCourseCountOnEn() throws DAOException {
        return getAllCategoryWithCourseCount(SqlQuery.GET_ALL_CATEGORY_WITH_COURSE_COUNT_EN);
    }

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
}
