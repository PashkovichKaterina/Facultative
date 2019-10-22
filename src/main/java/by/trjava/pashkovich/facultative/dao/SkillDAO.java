package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;

import java.util.Map;
import java.util.Set;

public interface SkillDAO {
    Set<String> getAllSkillOnRu() throws DAOException;

    Set<String> getAllSkillOnEn() throws DAOException;

    Set<String> getAllSkillLevelOnRu() throws DAOException;

    Set<String> getAllSkillLevelOnEn() throws DAOException;

    Map<String, Integer> getAllSkillWithCourseCountOnEn() throws DAOException;

    Map<String, Integer> getAllSkillWithCourseCountOnRu() throws DAOException;
}
