package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.UserService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchStudentCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SearchStudentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceFactory.getUserService();
        String studentName = request.getParameter(Variable.NAME);
        try {
            request.setAttribute(Variable.STUDENTS, userService.getStudentByPartialMatch(studentName));
            request.getRequestDispatcher(JspPath.ADMIN_STUDENT).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.warn("Exception to search student");
            response.sendError(500);
        }
    }
}
