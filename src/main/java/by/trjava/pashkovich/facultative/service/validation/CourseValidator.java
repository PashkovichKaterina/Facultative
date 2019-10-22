package by.trjava.pashkovich.facultative.service.validation;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.CourseDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class CourseValidator {
    public static boolean checkCourseId(int id) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return courseDAO.getCourseByIdOnEn(id) != null;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static boolean checkCategoryTitle(String category, String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? courseDAO.getCategoryIdByCategoryTitleOnEn(category) > 0
                    : courseDAO.getCategoryIdByCategoryTitleOnRu(category) > 0;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static boolean isCourseFixedForTeacher(int courseId, int teacherId) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return courseDAO.isCourseFixedForTeacher(courseId, teacherId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static boolean checkCourseTitleOnRu(String title, HttpServletRequest request) throws ServiceException {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(title)) {
            request.setAttribute(Variable.TITLE_RU_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkFormatForTextField(title)) {
            request.setAttribute(Variable.TITLE_RU_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            if (courseDAO.getCourseIdByRuTitle(title) > 0) {
                request.setAttribute(Variable.TITLE_RU_ERROR, messageManager.getMessage(InformMessage.COURSE_EXIST, local));
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    public static boolean checkCourseTitleOnEn(String title, HttpServletRequest request) throws ServiceException {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(title)) {
            request.setAttribute(Variable.TITLE_EN_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkFormatForTextField(title)) {
            request.setAttribute(Variable.TITLE_EN_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            if (courseDAO.getCourseIdByEnTitle(title) > 0) {
                request.setAttribute(Variable.TITLE_EN_ERROR, messageManager.getMessage(InformMessage.COURSE_EXIST, local));
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    public static boolean checkClassCount(String count, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(count) ) {
            request.setAttribute(Variable.COUNT_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.isWholeNumber(count) || new Integer(count) < 1) {
            request.setAttribute(Variable.COUNT_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        return true;
    }

    public static boolean checkDescriptionOnRu(String description, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(description)) {
            request.setAttribute(Variable.DESCRIPTION_RU_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (description.length() < 100 || description.length() > 500) {
            request.setAttribute(Variable.DESCRIPTION_RU_ERROR, messageManager.getMessage(InformMessage.INVALID_DESCRIPTION, local));
            return false;
        }
        return true;
    }

    public static boolean checkDescriptionOnEn(String description, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(description)) {
            request.setAttribute(Variable.DESCRIPTION_EN_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (description.length() < 100 || description.length() > 500) {
            request.setAttribute(Variable.DESCRIPTION_EN_ERROR, messageManager.getMessage(InformMessage.INVALID_DESCRIPTION, local));
            return false;
        }
        return true;
    }
}
