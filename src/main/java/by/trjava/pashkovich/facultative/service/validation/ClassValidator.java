package by.trjava.pashkovich.facultative.service.validation;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ClassDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class containing methods for checking values used in {@code ClassService} class.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @since JDK 1.0
 */
public class ClassValidator {
    /**
     * The method checks that the date field is not empty
     * and matches the format in accordance with {@code FieldValidator.checkDate} method.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message
     * is set to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.DATE_ERROR}.</p>
     *
     * @param date    date as a string to check.
     * @param request the {@code HttpServletRequest} object.
     * @return {@code true} if the variable is not empty, corresponds to the date format
     * and on this day at this course no classes were added before, {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    public static boolean checkDate(String date, int courseId, HttpServletRequest request) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        MessageManager messageManager = MessageManager.getInstance();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);

        if (FieldValidator.isEmpty(date)) {
            request.setAttribute(Variable.DATE_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkDate(date)) {
            request.setAttribute(Variable.DATE_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        try {
            if (classDAO.isContains(courseId, date)) {
                request.setAttribute(Variable.DATE_ERROR, messageManager.getMessage(InformMessage.EXIST_CLASS_DATE, local));
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    /**
     * The method checks that the time field is not empty
     * and matches the format in accordance with {@code FieldValidator.checkTime} method.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message
     * is set to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.TIME_ERROR}.</p>
     *
     * @param time    time as a string to check.
     * @param request the {@code HttpServletRequest} object.
     * @return {@code true} if the variable is not empty and corresponds to the time format,
     * {@code false} otherwise.
     */
    public static boolean checkTime(String time, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(time)) {
            request.setAttribute(Variable.TIME_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkTime(time)) {
            request.setAttribute(Variable.TIME_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        return true;
    }
}
