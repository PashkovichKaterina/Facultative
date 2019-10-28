package by.trjava.pashkovich.facultative.service.validation;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.UserDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Class containing methods for checking values used for check user parameters.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @since JDK 1.0
 */
public class UserValidator {
    /**
     * Checks that the login is not empty, matches the format {@code FieldValidator.checkTextFieldFormat}
     * and there are no users with the same login in the database.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.LOGIN_ERROR}.</p>
     *
     * @param login   login for verification
     * @param request the {@code HttpServletRequest} object.
     * @return if the login is not empty, it matches the format and there
     * are no users with the same login, {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    public static boolean checkLogin(String login, HttpServletRequest request) throws ServiceException {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        UserDAO userDAO = DAOFactory.getUserDAO();
        if (FieldValidator.isEmpty(login)) {
            request.setAttribute(Variable.LOGIN_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkFormatForTextField(login)) {
            request.setAttribute(Variable.LOGIN_ERROR, messageManager.getMessage(InformMessage.TEXT_FIELD_INVALID_TYPE, local));
            return false;
        }
        try {
            if (userDAO.getUserByLoginOnEn(login) != null) {
                request.setAttribute(Variable.LOGIN_ERROR, messageManager.getMessage(InformMessage.BUSY_LOGIN, local));
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    /**
     * Checks that the email is not empty, matches the format {@code FieldValidator.checkEmail}
     * and there are no users with the same email in the database.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.EMAIL_ERROR}.</p>
     *
     * @param email   email for verification
     * @param request the {@code HttpServletRequest} object.
     * @return if the email is not empty, it matches the format and there
     * are no users with the same email, {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    public static boolean checkEmail(String email, HttpServletRequest request) throws ServiceException {
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        UserDAO userDAO = DAOFactory.getUserDAO();
        if (FieldValidator.isEmpty(email)) {
            request.setAttribute(Variable.EMAIL_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            return false;
        }
        if (!FieldValidator.checkEmail(email)) {
            request.setAttribute(Variable.EMAIL_ERROR, messageManager.getMessage(InformMessage.INVALID_DATA_TYPE, local));
            return false;
        }
        try {
            if (userDAO.getUserByEmailOnEn(email) != null) {
                request.setAttribute(Variable.EMAIL_ERROR, messageManager.getMessage(InformMessage.BUSY_EMAIL, local));
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    /**
     * Checks that the password is not empty and matches the confirmation.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.PASSWORD_ERROR}.</p>
     *
     * @param password user entered password.
     * @param confirm  user entered password confirmation.
     * @param request  the {@code HttpServletRequest} object.
     * @return {@code true} if the password is not empty and matches the confirmation,
     * {@code false} otherwise.
     */
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

    /**
     * Checks that the surname is not empty, matches the format {@code FieldValidator.checkTextFieldFormat}
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.SURNAME_ERROR}.</p>
     *
     * @param surname surname for verification.
     * @param request the {@code HttpServletRequest} object.
     * @return if the surname is not empty and it matches the format, {@code false} otherwise.
     */
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

    /**
     * Checks that the name is not empty, matches the format {@code FieldValidator.checkTextFieldFormat}
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.NAME_ERROR}.</p>
     *
     * @param name    name for verification.
     * @param request the {@code HttpServletRequest} object.
     * @return if the name is not empty and it matches the format, {@code false} otherwise.
     */
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

    /**
     * Checks that the patronymic is not empty, matches the format {@code FieldValidator.checkTextFieldFormat}
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.PATRONYMIC_ERROR}.</p>
     *
     * @param patronymic patronymic for verification.
     * @param request    the {@code HttpServletRequest} object.
     * @return if the patronymic is not empty and it matches the format, {@code false} otherwise.
     */
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

    /**
     * Checks that the phone is not empty, matches the format {@code FieldValidator.checkPhone}
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.PHONE_ERROR}.</p>
     *
     * @param phone   phone for verification.
     * @param request the {@code HttpServletRequest} object.
     * @return if the phone is not empty and it matches the format, {@code false} otherwise.
     */
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

    /**
     * Checks if the field is not empty, then must match the format {@code FieldValidator.checkDate}.
     *
     * <p>If the variable does not satisfy the condition, the corresponding message is set
     * to the {@code HttpServletRequest} object as an attribute with the name {@code Variable.DATE_ERROR}.</p>
     *
     * @param date    date for verification.
     * @param request the {@code HttpServletRequest} object.
     * @return if the date is not empty and it matches the format, {@code false} otherwise.
     */
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