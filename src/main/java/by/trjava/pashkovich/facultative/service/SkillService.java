package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;
import java.util.Set;

public interface SkillService {
    Set<String> getAllSkill(String local) throws ServiceException;

    Set<String> getAllSkillLevel(String local) throws ServiceException;

    Map<String, Integer> getAllSkillWithCourseCount(String local) throws ServiceException;
}
