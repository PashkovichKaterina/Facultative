package by.trjava.pashkovich.facultative.controller.command.impl.teacher;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthorizationException;
import by.trjava.pashkovich.facultative.controller.command.validation.UserRoleValidator;
import by.trjava.pashkovich.facultative.dao.ArchiveDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.ArchiveService;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.UserService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowLeaveReviewFormCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowLeaveReviewFormCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        UserService userService = ServiceFactory.getUserService();
        ArchiveService archiveService = ServiceFactory.getArchiveService();
        CourseService courseService = ServiceFactory.getCourseService();

        try {
            int studentId = new Integer(request.getParameter(Variable.STUDENT));
            int courseId = new Integer(request.getParameter(Variable.COURSE));
            request.setAttribute(Variable.STUDENT, userService.getUserById(studentId, local));
            request.setAttribute(Variable.COURSE, courseService.getCourseById(courseId, local));
            request.setAttribute(Variable.REVIEW, archiveService.getReview(studentId, courseId));
            request.getRequestDispatcher(JspPath.LEAVE_REVIEW).forward(request, response);
        } catch (NumberFormatException | ServiceException e) {
            LOGGER.error(e.getMessage());
            response.sendError(500);
        }
    }
}
