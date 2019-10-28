package by.trjava.pashkovich.facultative.controller.command.impl.teacher;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthorizationException;
import by.trjava.pashkovich.facultative.controller.command.validation.UserRoleValidator;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.MarkService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command is used to show add edit mark form for teacher.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class ShowEditMarkFormCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowEditMarkFormCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        MarkService markService = ServiceFactory.getMarkService();
        HttpSession session = request.getSession();
        String workTitle = request.getParameter(Variable.WORK);
        String local = (String) session.getAttribute(Variable.LOCAL);
        User user = (User) session.getAttribute(Variable.USER);
        try {
            if (UserRoleValidator.isUserLoggedIn(user) && UserRoleValidator.isTeacherLoggedIn(user)) {
                int courseId = new Integer(request.getParameter(Variable.ID));
                request.setAttribute(Variable.COURSE, courseService.getCourseById(courseId, local));
                request.setAttribute(Variable.WORK, workTitle);
                request.setAttribute(Variable.STUDENTS, markService.getStudentWithMarkByCourseWork(courseId, workTitle));
                request.getRequestDispatcher(JspPath.COURSE_TEACHER_EDIT_PAGE).forward(request, response);
            }
        } catch (AuthenticationException e) {
            LOGGER.warn("Unauthenticated user tried to access the page " + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + JspPath.LOGIN_PAGE);
        } catch (AuthorizationException e) {
            LOGGER.warn("User " + user.getId() + " tried to access the page " + request.getRequestURI());
            response.sendError(404);
        } catch (NumberFormatException | ServiceException e) {
            response.sendError(500);
            LOGGER.error("Error to show edit mark form: " + e.getMessage());
        }
    }
}

