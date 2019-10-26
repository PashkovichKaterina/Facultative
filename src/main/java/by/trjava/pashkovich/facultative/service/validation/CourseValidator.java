package by.trjava.pashkovich.facultative.service.validation;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.CategoryDAO;
import by.trjava.pashkovich.facultative.dao.CourseDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Class containing methods for checking values used by {@code Course} class.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @since JDK 1.0
 */
public class CourseValidator {
    /**
     * Minimum length for course description.
     */
    private static final int DESCRIPTION_MIN_LENGTH = 100;

    /**
     * Maximum length for course description.
     */
    private static final int DESCRIPTION_MAX_LENGTH = 500;

    private static final int MIN_COURSE_CLASSES_COUNT = 1;

    /**
     * Checks if the database contains the course with the specified id.
     *
     * @param id course id number.
     * @return {@code true} if the database contains the course with the specified id, {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    public static boolean checkCourseId(int id) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return courseDAO.getCourseByIdOnEn(id) != null;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Checks if the database contains the category with the specified title.
     *
     * @param category the category title.
     * @param local    the language to be used for verification.
     * @return {@code true} if the category with that title is contained in the database, {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    public static boolean checkCategoryTitle(String category, String local) throws ServiceException {
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO();
        int categoryId;
        try {
            categoryId = MessageManager.enLocal.equals(local)
                    ? categoryDAO.getCategoryIdByCategoryTitleOnEn(category)
                    : categoryDAO.getCategoryIdByCategoryTitleOnRu(category);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return categoryId > 0;
    }

    /**
     * Checks if the specific course is fixed to the specific teacher.
     *
     * @param courseId  course id for verification.
     * @param teacherId teacher id for verification.
     * @return {@code true} if the specific course is fixed to the specific teacher, {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    public static boolean isCourseFixedForTeacher(int courseId, int teacherId) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return courseDAO.isCourseFixedForTeacher(courseId, teacherId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * The method checks that the course title is not empty
     * corresponds to the format by {@code FieldValidator.checkFormatForTextField} method
     * and the database does not have a course with the same title in Russian language.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.TITLE_RU_ERROR}.</p>
     *
     * @param title   course title on Russian language.
     * @param request the {@code HttpServletRequest} object.
     * @return {@code true} if the course title is not empty, corresponds to the format
     * and the database does not have a course with the same title in Russian language, {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * The method checks that the course title is not empty
     * corresponds to the format by {@code FieldValidator.checkFormatForTextField} method
     * and the database does not have a course with the same title in English language.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.TITLE_RU_ERROR}.</p>
     *
     * @param title   course title on English language.
     * @param request the {@code HttpServletRequest} object.
     * @return {@code true} if the course title is not empty, corresponds to the format
     * and the database does not have a course with the same title in English language, {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * If the variable is not empty and contains a positive integer greater than {@value MIN_COURSE_CLASSES_COUNT}.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.COUNT_ERROR}.</p>
     *
     * @param count   string representation of the course class count.
     * @param request the {@code HttpServletRequest} object.
     * @return {@code true} if the variable is not empty and contains a positive integer
     * satisfying the condition, {@code false} otherwise.
     */
    public static boolean checkClassCount(String count, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(count)) {
            request.setAttribute(Variable.COUNT_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.isWholeNumber(count) || new Integer(count) < MIN_COURSE_CLASSES_COUNT) {
            request.setAttribute(Variable.COUNT_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        return true;
    }

    /**
     * The method checks that the course description is not empty
     * corresponds to the format by {@code FieldValidator.checkFormatForTextField} method
     * and contains a number of characters greater
     * than {@value DESCRIPTION_MIN_LENGTH} and less than {@value DESCRIPTION_MAX_LENGTH}.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.DESCRIPTION_RU_ERROR}.</p>
     *
     * @param description course description on Russian language.
     * @param request     the {@code HttpServletRequest} object.
     * @return {@code true} if the field is not empty, it matches the format
     * and the required number of characters, {@code false} otherwise.
     */
    public static boolean checkDescriptionOnRu(String description, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(description)) {
            request.setAttribute(Variable.DESCRIPTION_RU_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkTextFieldFormat(description)) {
            request.setAttribute(Variable.DESCRIPTION_RU_ERROR, messageManager.getMessage(InformMessage.TEXT_FIELD_INVALID_TYPE, local));
            return false;
        }
        if (description.length() < DESCRIPTION_MIN_LENGTH || description.length() > DESCRIPTION_MAX_LENGTH) {
            request.setAttribute(Variable.DESCRIPTION_RU_ERROR, messageManager.getMessage(InformMessage.INVALID_DESCRIPTION, local));
            return false;
        }
        return true;
    }

    /**
     * The method checks that the course description is not empty
     * corresponds to the format by {@code FieldValidator.checkFormatForTextField} method
     * and contains a number of characters greater
     * than {@value DESCRIPTION_MIN_LENGTH} and less than {@value DESCRIPTION_MAX_LENGTH}.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.DESCRIPTION_RU_ERROR}.</p>
     *
     * @param description course description on English language.
     * @param request     the {@code HttpServletRequest} object.
     * @return {@code true} if the field is not empty, it matches the format
     * and the required number of characters, {@code false} otherwise.
     */
    public static boolean checkDescriptionOnEn(String description, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(description)) {
            request.setAttribute(Variable.DESCRIPTION_EN_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkTextFieldFormat(description)) {
            request.setAttribute(Variable.DESCRIPTION_EN_ERROR, messageManager.getMessage(InformMessage.TEXT_FIELD_INVALID_TYPE, local));
            return false;
        }
        if (description.length() < DESCRIPTION_MIN_LENGTH || description.length() > DESCRIPTION_MAX_LENGTH) {
            request.setAttribute(Variable.DESCRIPTION_EN_ERROR, messageManager.getMessage(InformMessage.INVALID_DESCRIPTION, local));
            return false;
        }
        return true;
    }
}
