package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.SkillDAO;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SkillDAOImpl implements SkillDAO {

    @Override
    public Set<String> getAllSkillOnRu() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<String> skills = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_SKILL_RU)) {
                    while (resultSet.next()) {
                        String skillTitle = resultSet.getString(Variable.TITLE);
                        skills.add(skillTitle);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return skills;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<String> getAllSkillOnEn() throws DAOException{
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<String> skills = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_SKILL_EN)) {
                    while (resultSet.next()) {
                        String skillTitle = resultSet.getString(Variable.TITLE);
                        skills.add(skillTitle);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return skills;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<String> getAllSkillLevelOnRu() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<String> levels = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_SKILL_LEVEL_RU)) {
                    while (resultSet.next()) {
                        String skillTitle = resultSet.getString(Variable.TITLE);
                        levels.add(skillTitle);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return levels;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<String> getAllSkillLevelOnEn() throws DAOException{
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Set<String> levels = new HashSet<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_SKILL_LEVEL_EN)) {
                    while (resultSet.next()) {
                        String skillTitle = resultSet.getString(Variable.TITLE);
                        levels.add(skillTitle);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return levels;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Integer> getAllSkillWithCourseCountOnRu() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<String, Integer> skills = new HashMap<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_SKILL_WITH_COURSE_COUNT_RU)) {
                    while (resultSet.next()) {
                        String skillTitle = resultSet.getString(Variable.TITLE);
                        int count = resultSet.getInt(Variable.COUNT);
                        skills.put(skillTitle, count);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return skills;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Integer> getAllSkillWithCourseCountOnEn() throws DAOException {
        try {
            Connection connection = BaseConnectionPool.getInstance().getConnection();
            Map<String, Integer> skills = new HashMap<>();
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SqlQuery.GET_ALL_SKILL_WITH_COURSE_COUNT_EN)) {
                    while (resultSet.next()) {
                        String skillTitle = resultSet.getString(Variable.TITLE);
                        int count = resultSet.getInt(Variable.COUNT);
                        skills.put(skillTitle, count);
                    }
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in SQL: " + e.getMessage(), e);
            }
            BaseConnectionPool.getInstance().releaseConnection(connection);
            return skills;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
    }
}
