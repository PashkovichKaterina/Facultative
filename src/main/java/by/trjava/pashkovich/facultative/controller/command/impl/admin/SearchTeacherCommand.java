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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SearchTeacherCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SearchTeacherCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceFactory.getUserService();
        HttpSession session = request.getSession();
        String teacherName = request.getParameter(Variable.NAME);
        String local = (String) session.getAttribute(Variable.LOCAL);
        try {
            request.setAttribute(Variable.TEACHERS, userService.getTeacherWithCourseCountByPartialMatch(teacherName, local));
            request.getRequestDispatcher(JspPath.ADMIN_TEACHER).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.warn("Exception to search teacher");
            response.sendError(500);
        }
    }
}
