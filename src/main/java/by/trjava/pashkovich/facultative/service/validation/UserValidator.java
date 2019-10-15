package by.trjava.pashkovich.facultative.service.validation;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.UserDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class UserValidator {
    public static boolean checkLogin(String login, HttpServletRequest request) throws ServiceException {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        UserDAO userDAO = DAOFactory.getUserDAO();
        if (FieldValidator.isEmpty(login)) {
            request.setAttribute(Variable.LOGIN_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        try {
            if (userDAO.getUserByLogin(login) != null) {
                request.setAttribute(Variable.LOGIN_ERROR, messageManager.getMessage(InformMessage.BUSY_LOGIN, local));
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    public static boolean checkEmail(String email, HttpServletRequest request) throws ServiceException {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        UserDAO userDAO = DAOFactory.getUserDAO();
        if (FieldValidator.isEmpty(email)) {
            request.setAttribute(Variable.EMAIL_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        try {
            if (userDAO.getUserByEmail(email) != null) {
                request.setAttribute(Variable.EMAIL_ERROR, messageManager.getMessage(InformMessage.BUSY_EMAIL, local));
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (!FieldValidator.checkEmail(email)) {
            request.setAttribute(Variable.EMAIL_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        return true;
    }

    public static boolean checkPassword(String password, String confirm, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(password)) {
            request.setAttribute(Variable.PASSWORD_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!password.equals(confirm)) {
            request.setAttribute(Variable.PASSWORD_ERROR, messageManager.getMessage(InformMessage.CONFIRM_ERROR, local));
            request.setAttribute(Variable.CONFIRM_ERROR, messageManager.getMessage(InformMessage.CONFIRM_ERROR, local));
            return false;
        }
        return true;
    }

    public static boolean checkSurname(String surname, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(surname)) {
            request.setAttribute(Variable.SURNAME_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkTextFieldFormat(surname)) {
            request.setAttribute(Variable.SURNAME_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        return true;
    }

    public static boolean checkName(String name, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(name)) {
            request.setAttribute(Variable.NAME_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkTextFieldFormat(name)) {
            request.setAttribute(Variable.NAME_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        return true;
    }

    public static boolean checkPatronymic(String patronymic, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(patronymic)) {
            request.setAttribute(Variable.PATRONYMIC_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkTextFieldFormat(patronymic)) {
            request.setAttribute(Variable.PATRONYMIC_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        return true;
    }

    public static boolean checkPhone(String phone, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (FieldValidator.isEmpty(phone)) {
            request.setAttribute(Variable.PHONE_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkPhone(phone)) {
            request.setAttribute(Variable.PHONE_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        return true;
    }

    public static boolean checkDate(String date, HttpServletRequest request) {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        if (!FieldValidator.isEmpty(date) && !FieldValidator.checkDate(date)) {
            request.setAttribute(Variable.DATE_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        return true;
    }
}
