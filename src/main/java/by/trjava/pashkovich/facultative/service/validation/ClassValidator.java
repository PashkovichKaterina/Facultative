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

public class ClassValidator {
    public static boolean checkDate(String date, HttpServletRequest request) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        MessageManager messageManager = MessageManager.getInstance();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);
        int courseId = new Integer(request.getParameter(Variable.ID));
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
