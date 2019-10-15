package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.UserDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristics.UserRole;
import by.trjava.pashkovich.facultative.service.UserService;
import by.trjava.pashkovich.facultative.service.encrypting.Encrypt;
import by.trjava.pashkovich.facultative.service.exception.EditStudentDataException;
import by.trjava.pashkovich.facultative.service.exception.RegistrationException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.validation.FieldValidator;
import by.trjava.pashkovich.facultative.service.validation.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
    @Override
    public void login(String login, String password) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        if (FieldValidator.isEmpty(login) || FieldValidator.isEmpty(password)) {
            throw new ServiceException("Empty data");
        }

        String originalPassword = null;
        try {
            originalPassword = userDAO.getPasswordByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (FieldValidator.isEmpty(originalPassword)) {
            throw new ServiceException("No user with such login");
        }
        if (!Encrypt.validatePassword(password, originalPassword)) {
            throw new ServiceException("Incorrect password");
        }
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        try {
            return userDAO.getUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void registration(HttpServletRequest request) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();

        String login = request.getParameter(Variable.LOGIN).trim();
        String email = request.getParameter(Variable.EMAIL).trim();
        String password = request.getParameter(Variable.PASSWORD).trim();
        String confirm = request.getParameter(Variable.CONFIRM).trim();

        if (UserValidator.checkLogin(login, request) & UserValidator.checkEmail(email, request)
                & UserValidator.checkPassword(password, confirm, request)) {
            String passwordHash = Encrypt.generatePasswordHash(password);
            try {
                userDAO.insertUser(login, email, passwordHash, UserRole.STUDENT);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        throw new RegistrationException();
    }

    @Override
    public void editStudent(HttpServletRequest request) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();

        String surname = request.getParameter(Variable.SURNAME).trim();
        String name = request.getParameter(Variable.NAME).trim();
        String patronymic = request.getParameter(Variable.PATRONYMIC).trim();
        String phone = request.getParameter(Variable.PHONE).trim();
        String date = request.getParameter(Variable.DATE).trim();
        String address = request.getParameter(Variable.ADDRESS).trim();
        if (UserValidator.checkSurname(surname, request) & UserValidator.checkName(name, request)
                & UserValidator.checkPatronymic(patronymic, request) & UserValidator.checkPhone(phone, request)
                & UserValidator.checkDate(date, request)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Variable.USER);
            try {
                if (userDAO.isContainsUserDetailsByLogin(user.getLogin())) {
                    userDAO.updateUserDetails(user.getId(), surname, name, patronymic, phone, date, address, null);
                } else {
                    userDAO.insertUserDetails(user.getId(), surname, name, patronymic, phone, date, address, null);
                }
                user = userDAO.getUserById(user.getId());
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
            session.setAttribute(Variable.USER, user);
        } else {
            request.setAttribute(Variable.SURNAME, surname);
            request.setAttribute(Variable.NAME, name);
            request.setAttribute(Variable.PATRONYMIC, patronymic);
            request.setAttribute(Variable.PHONE, phone);
            request.setAttribute(Variable.DATE, date);
            request.setAttribute(Variable.ADDRESS, address);
            throw new EditStudentDataException();
        }
    }
}
