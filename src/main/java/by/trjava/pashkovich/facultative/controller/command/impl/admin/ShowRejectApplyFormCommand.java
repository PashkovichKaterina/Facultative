package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.UserService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowRejectApplyFormCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowRejectApplyFormCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        UserService userService = ServiceFactory.getUserService();
        CourseService courseService = ServiceFactory.getCourseService();
        try {
            int studentId = new Integer(request.getParameter(Variable.STUDENT));
            int courseId = new Integer(request.getParameter(Variable.COURSE));
            request.setAttribute(Variable.STUDENT, userService.getUserById(studentId, local));
            request.setAttribute(Variable.COURSE, courseService.getCourseById(courseId, local));
            request.getRequestDispatcher(JspPath.REJECT_APPLY_PAGE).forward(request, response);
        } catch (NumberFormatException | ServiceException e) {
            LOGGER.error(e.getMessage());
            response.sendError(500);
        }
    }
}
