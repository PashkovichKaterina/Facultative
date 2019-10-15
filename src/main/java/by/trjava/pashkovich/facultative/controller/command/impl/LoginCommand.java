package by.trjava.pashkovich.facultative.controller.command.impl;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.UserService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceFactory.getUserService();
        HttpSession session = request.getSession();

        String login = request.getParameter(Variable.LOGIN).trim();
        String password = request.getParameter(Variable.PASSWORD).trim();

        try {
            userService.login(login, password);
            session.setAttribute(Variable.USER, userService.getUserByLogin(login));
            response.sendRedirect(request.getContextPath() + JspPath.INDEX_PAGE);
        } catch (ServiceException e) {
            MessageManager messageManager = MessageManager.getInstance();
            String local = (String) session.getAttribute(Variable.LOCAL);
            request.setAttribute(Variable.INFORM_MESSAGE, messageManager.getMessage(InformMessage.INVALID_LOGIN_DATA, local));
            request.getRequestDispatcher(JspPath.LOGIN_PAGE).forward(request, response);
        }
    }
}
