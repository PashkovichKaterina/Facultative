package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;
import java.util.Set;

public interface SkillService {
    /**
     * Returns all skills that are defined in the database.
     *
     * @param local language used by the user.
     * @return all skills that are defined in the database.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<String> getAllSkill(String local) throws ServiceException;

    /**
     * Returns all skill levels that are defined in the database.
     *
     * @param local language used by the user.
     * @return all skill levels that are defined in the database.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<String> getAllSkillLevel(String local) throws ServiceException;

    /**
     * Returns all skills that are defined in the database with the number of classes for each skill.
     *
     * @param local language used by the user.
     * @return all skills that are defined in the database with the number of classes for each skill.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<String, Integer> getAllSkillWithCourseCount(String local) throws ServiceException;
}
