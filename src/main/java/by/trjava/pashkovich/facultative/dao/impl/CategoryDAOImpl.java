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
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<String, Integer> categories = new HashMap<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_CATEGORY_WITH_COURSE_COUNT_RU)) {
                    while (resultSet.next()) {
                        String skillTitle = resultSet.getString(Variable.TITLE);
                        int count = resultSet.getInt(Variable.COUNT);
                        categories.put(skillTitle,count);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return categories;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }
    @Override
    public Map<String, Integer> getAllCategoryWithCourseCountOnEn() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<String, Integer> categories = new HashMap<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_CATEGORY_WITH_COURSE_COUNT_EN)) {
                    while (resultSet.next()) {
                        String skillTitle = resultSet.getString(Variable.TITLE);
                        int count = resultSet.getInt(Variable.COUNT);
                        categories.put(skillTitle,count);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return categories;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }
}
