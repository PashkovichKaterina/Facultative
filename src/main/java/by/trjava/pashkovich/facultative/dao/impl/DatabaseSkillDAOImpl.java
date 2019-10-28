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

public class DatabaseSkillDAOImpl implements SkillDAO {
    /**
     * Returns all skill on Russian language which are stored in the database
     * used {@code SqlQuery.GET_ALL_SKILL_RU} SQL query.
     *
     * @return all skill on Russian language which are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<String> getAllSkillOnRu() throws DAOException {
        return getAllStringParameterByQuery(SqlQuery.GET_ALL_SKILL_RU);
    }

    /**
     * Returns all skill on English language which are stored in the database
     * used {@code SqlQuery.GET_ALL_SKILL_EN} SQL query.
     *
     * @return all skill on English language which are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<String> getAllSkillOnEn() throws DAOException {
        return getAllStringParameterByQuery(SqlQuery.GET_ALL_SKILL_EN);
    }

    /**
     * Returns all skill level on Russian language which are stored in the database
     * used {@code SqlQuery.GET_ALL_SKILL_LEVEL_RU} SQL query.
     *
     * @return all skill level on Russian language which are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<String> getAllSkillLevelOnRu() throws DAOException {
        return getAllStringParameterByQuery(SqlQuery.GET_ALL_SKILL_LEVEL_RU);
    }

    /**
     * Returns all skill level on English language which are stored in the database
     * used {@code SqlQuery.GET_ALL_SKILL_LEVEL_EN} SQL query.
     *
     * @return all skill level on English language which are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Set<String> getAllSkillLevelOnEn() throws DAOException {
        return getAllStringParameterByQuery(SqlQuery.GET_ALL_SKILL_LEVEL_EN);
    }

    /**
     * Returns all string parameter which are defined in the specific query.
     *
     * @param query specific query.
     * @return all string parameter which are defined in the specific query.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    private Set<String> getAllStringParameterByQuery(String query) throws DAOException {
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

    /**
     * Returns all skill on Russian language which are stored in the database
     * along with the number of courses used {@code SqlQuery.GET_ALL_SKILL_WITH_COURSE_COUNT_RU} SQL query.
     *
     * @return all skill on Russian language which are stored in the database
     * along with the number of courses.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<String, Integer> getAllSkillWithCourseCountOnRu() throws DAOException {
        return getAllSkillWithCourseCount(SqlQuery.GET_ALL_SKILL_WITH_COURSE_COUNT_RU);
    }

    /**
     * Returns all skill on English language which are stored in the database
     * along with the number of courses used {@code SqlQuery.GET_ALL_SKILL_WITH_COURSE_COUNT_EN} SQL query.
     *
     * @return all skill on English language which are stored in the database
     * along with the number of courses.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    @Override
    public Map<String, Integer> getAllSkillWithCourseCountOnEn() throws DAOException {
        return getAllSkillWithCourseCount(SqlQuery.GET_ALL_SKILL_WITH_COURSE_COUNT_EN);
    }

    /**
     * Returns all skill which are stored in the database along with the number of courses
     * used specific SQL query.
     *
     * @param query specific query.
     * @return all skill which are stored in the database along with the number of courses.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
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
                    int count = resultSet.getInt(Variable.COUNT);
                    String skillTitle = resultSet.getString(Variable.TITLE);
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
