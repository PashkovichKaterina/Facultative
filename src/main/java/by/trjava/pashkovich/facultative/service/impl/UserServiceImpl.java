package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.UserDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.Teacher;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;
import by.trjava.pashkovich.facultative.service.UserService;
import by.trjava.pashkovich.facultative.service.comparator.PersonComparator;
import by.trjava.pashkovich.facultative.service.encrypting.Encrypt;
import by.trjava.pashkovich.facultative.service.exception.EditStudentDataException;
import by.trjava.pashkovich.facultative.service.exception.RegistrationException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;
import by.trjava.pashkovich.facultative.service.validation.FieldValidator;
import by.trjava.pashkovich.facultative.service.validation.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class UserServiceImpl implements UserService {
    @Override
    public void login(String login, String password) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        if (FieldValidator.isEmpty(login) || FieldValidator.isEmpty(password)) {
            throw new ServiceException("Empty data");
        }

        String originalPassword;
        try {
            originalPassword = userDAO.getPasswordByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        if (FieldValidator.isEmpty(originalPassword)) {
            throw new ServiceException("No user with such login");
        }
        if (!Encrypt.validatePassword(password, originalPassword)) {
            throw new ServiceException("Incorrect password");
        }
    }

    @Override
    public User getUserByLogin(String login, String local) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? userDAO.getUserByLoginOnEn(login)
                    : userDAO.getUserByLoginOnRu(login);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User getUserById(int userId, String local) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? userDAO.getUserByIdOnRu(userId)
                    : userDAO.getUserByIdOnRu(userId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
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
                throw new ServiceException(e.getMessage(), e);
            }
        } else {
            throw new RegistrationException("Invalid data for registration");
        }
    }

    @Override
    public void editStudent(HttpServletRequest request) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        String local = (String) session.getAttribute(Variable.LOCAL);

        String surname = request.getParameter(Variable.SURNAME).trim();
        String name = request.getParameter(Variable.NAME).trim();
        String patronymic = request.getParameter(Variable.PATRONYMIC).trim();
        String phone = request.getParameter(Variable.PHONE).trim();
        String date = request.getParameter(Variable.DATE).trim();
        if (FieldValidator.isEmpty(date)) {
            date = null;
        }
        String address = request.getParameter(Variable.ADDRESS).trim();

        if (UserValidator.checkSurname(surname, request) & UserValidator.checkName(name, request)
                & UserValidator.checkPatronymic(patronymic, request) & UserValidator.checkPhone(phone, request)
                & UserValidator.checkDate(date, request)) {
            try {
                if (userDAO.isContainsUserDetailsByLogin(user.getLogin())) {
                    userDAO.updateUserDetails(user.getId(), surname, name, patronymic, phone, date, address, null);
                } else {
                    userDAO.insertUserDetails(user.getId(), surname, name, patronymic, phone, date, address, null);
                }
                user = MessageManager.enLocal.equals(local) ? userDAO.getUserByIdOnEn(user.getId())
                        : userDAO.getUserByIdOnRu(user.getId());
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage(), e);
            }
            session.setAttribute(Variable.USER, user);
        } else {
            request.setAttribute(Variable.SURNAME, surname);
            request.setAttribute(Variable.NAME, name);
            request.setAttribute(Variable.PATRONYMIC, patronymic);
            request.setAttribute(Variable.PHONE, phone);
            request.setAttribute(Variable.DATE, date);
            request.setAttribute(Variable.ADDRESS, address);
            throw new EditStudentDataException("Invalid data for edit student account");
        }
    }

    @Override
    public Set<Student> getAllStudent() throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        Set<Student> students = new TreeSet<>(new PersonComparator<>());
        try {
            for (Student student : userDAO.getAllStudent()) {
                students.add(student);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return students;
    }

    @Override
    public Set<Student> getStudentByPartialMatch(String studentName) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        Set<Student> students = new TreeSet<>(new PersonComparator<>());
        try {
            for (Student student : userDAO.getStudentByPartialMatch(studentName)) {
                students.add(student);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return students;
    }

    @Override
    public Set<Teacher> getAllTeacher(String local) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        Set<Teacher> teachers = new TreeSet<>(new PersonComparator<>());
        try {
            for (Teacher teacher : MessageManager.enLocal.equals(local)
                    ? userDAO.getAllTeacherOnEn()
                    : userDAO.getAllTeacherOnRu()) {
                teachers.add(teacher);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return teachers;
    }

    @Override
    public Map<Teacher, Integer> getAllTeacherWithCourseCount(String local) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        Map<Teacher, Integer> teachers = new TreeMap<>(new PersonComparator<>());
        try {
            for (Map.Entry<Teacher, Integer> teacher : MessageManager.enLocal.equals(local)
                    ? userDAO.getAllTeacherWithCourseCountOnEn().entrySet()
                    : userDAO.getAllTeacherWithCourseCountOnRu().entrySet()) {
                teachers.put(teacher.getKey(), teacher.getValue());
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return teachers;
    }

    @Override
    public Map<Teacher, Integer> getTeacherWithCourseCountByPartialMatch(String teacherName, String local) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        Map<Teacher, Integer> teachers = new TreeMap<>(new PersonComparator<>());
        try {
            for (Map.Entry<Teacher, Integer> teacher : MessageManager.enLocal.equals(local)
                    ? userDAO.getTeacherWithCourseCountByPartialMatchOnEn(teacherName).entrySet()
                    : userDAO.getTeacherWithCourseCountByPartialMatchOnRu(teacherName).entrySet()) {
                teachers.put(teacher.getKey(), teacher.getValue());
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return teachers;
    }
}
