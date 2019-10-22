package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.SkillDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.SkillService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;

import java.util.Map;
import java.util.Set;

public class SkillServiceImpl implements SkillService {
    @Override
    public Set<String> getAllSkill(String local) throws ServiceException {
        SkillDAO skillDAO = DAOFactory.getSkillDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? skillDAO.getAllSkillOnEn()
                    : skillDAO.getAllSkillOnRu();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Set<String> getAllSkillLevel(String local) throws ServiceException {
        SkillDAO skillDAO = DAOFactory.getSkillDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? skillDAO.getAllSkillLevelOnEn()
                    : skillDAO.getAllSkillLevelOnRu();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Integer> getAllSkillWithCourseCount(String local) throws ServiceException {
        SkillDAO skillDAO = DAOFactory.getSkillDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? skillDAO.getAllSkillWithCourseCountOnEn()
                    : skillDAO.getAllSkillWithCourseCountOnRu();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
