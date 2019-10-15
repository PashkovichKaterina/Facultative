package by.trjava.pashkovich.facultative.controller.command.impl;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.UserService;
import by.trjava.pashkovich.facultative.service.exception.RegistrationException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();

        String login = request.getParameter(Variable.LOGIN).trim();
        String email = request.getParameter(Variable.EMAIL).trim();

        try {
            userService.registration(request);
            HttpSession session = request.getSession();
            session.setAttribute(Variable.USER, userService.getUserByLogin(login));
            request.getRequestDispatcher(JspPath.EDIT_STUDENT_PAGE).forward(request, response);
        } catch (RegistrationException e) {
            request.setAttribute(Variable.LOGIN, login);
            request.setAttribute(Variable.EMAIL, email);
            request.getRequestDispatcher(JspPath.REGISTRATION_PAGE).forward(request, response);
        } catch (ServiceException e) {
            response.sendError(500);
        }
    }
}
