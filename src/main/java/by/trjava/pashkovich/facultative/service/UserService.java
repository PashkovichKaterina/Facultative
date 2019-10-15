package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    void login(String login, String password) throws ServiceException;

    void registration(HttpServletRequest request) throws ServiceException;

    void editStudent(HttpServletRequest request) throws ServiceException;

    User getUserByLogin(String login) throws ServiceException;
}
