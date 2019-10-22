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
        return getAllSkill(SqlQuery.GET_ALL_SKILL_RU);
    }

    @Override
    public Set<String> getAllSkillOnEn() throws DAOException {
        return getAllSkill(SqlQuery.GET_ALL_SKILL_EN);
    }

    @Override
    public Set<String> getAllSkillLevelOnRu() throws DAOException {
        return getAllSkill(SqlQuery.GET_ALL_SKILL_LEVEL_RU);
    }

    @Override
    public Set<String> getAllSkillLevelOnEn() throws DAOException {
        return getAllSkill(SqlQuery.GET_ALL_SKILL_LEVEL_EN);
    }

    private Set<String> getAllSkill(String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Set<String> skills = new HashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String skillTitle = resultSet.getString(Variable.TITLE);
                    skills.add(skillTitle);
                }
                return skills;
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

    @Override
    public Map<String, Integer> getAllSkillWithCourseCountOnRu() throws DAOException {
        return getAllSkillWithCourseCount(SqlQuery.GET_ALL_SKILL_WITH_COURSE_COUNT_RU);
    }

    @Override
    public Map<String, Integer> getAllSkillWithCourseCountOnEn() throws DAOException {
        return getAllSkillWithCourseCount(SqlQuery.GET_ALL_SKILL_WITH_COURSE_COUNT_EN);
    }

    private Map<String, Integer> getAllSkillWithCourseCount(String query) throws DAOException {
        Connection connection;
        try {
            connection = BaseConnectionPool.getInstance().getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool: " + e.getMessage(), e);
        }
        Map<String, Integer> skills = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String skillTitle = resultSet.getString(Variable.TITLE);
                    int count = resultSet.getInt(Variable.COUNT);
                    skills.put(skillTitle, count);
                }
                return skills;
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
