package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;

import java.util.Map;
import java.util.Set;

public interface SkillDAO {
    /**
     * Returns all skill on Russian language which are stored in the database.
     *
     * @return all skill on Russian language which are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<String> getAllSkillOnRu() throws DAOException;

    /**
     * Returns all skill on English language which are stored in the database.
     *
     * @return all skill on English language which are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<String> getAllSkillOnEn() throws DAOException;

    /**
     * Returns all skill level on Russian language which are stored in the database.
     *
     * @return all skill level on Russian language which are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<String> getAllSkillLevelOnRu() throws DAOException;

    /**
     * Returns all skill level on English language which are stored in the database.
     *
     * @return all skill level on English language which are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<String> getAllSkillLevelOnEn() throws DAOException;

    /**
     * Returns all skill on Russian language which are stored in the database
     * along with the number of courses.
     *
     * @return all skill on Russian language which are stored in the database
     * along with the number of courses.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<String, Integer> getAllSkillWithCourseCountOnEn() throws DAOException;

    /**
     * Returns all skill on English language which are stored in the database
     * along with the number of courses.
     *
     * @return all skill on English language which are stored in the database
     * along with the number of courses.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<String, Integer> getAllSkillWithCourseCountOnRu() throws DAOException;
}
