package by.trjava.pashkovich.facultative.controller.command.impl;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.UserService;
import by.trjava.pashkovich.facultative.service.exception.RegistrationException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);

        String login = request.getParameter(Variable.LOGIN).trim();
        String email = request.getParameter(Variable.EMAIL).trim();

        try {
            userService.registration(request);
            session.setAttribute(Variable.USER, userService.getUserByLogin(login, local));
            request.getRequestDispatcher(JspPath.EDIT_STUDENT_PAGE).forward(request, response);
        } catch (RegistrationException e) {
            LOGGER.debug("Unsuccessful registration");
            request.setAttribute(Variable.LOGIN, login);
            request.setAttribute(Variable.EMAIL, email);
            request.getRequestDispatcher(JspPath.REGISTRATION_PAGE).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.error("Registration error: " + e.getMessage());
            response.sendError(500);
        }
    }
}
