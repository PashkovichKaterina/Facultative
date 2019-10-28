package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthorizationException;
import by.trjava.pashkovich.facultative.controller.command.validation.UserRoleValidator;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.UserService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command is used to show form to reject apply for administrator.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class ShowRejectApplyFormCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowRejectApplyFormCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        String local = (String) session.getAttribute(Variable.LOCAL);
        UserService userService = ServiceFactory.getUserService();
        CourseService courseService = ServiceFactory.getCourseService();

        try {
            if (UserRoleValidator.isUserLoggedIn(user) && UserRoleValidator.isAdministratorLoggedIn(user)) {
                int studentId = new Integer(request.getParameter(Variable.STUDENT));
                int courseId = new Integer(request.getParameter(Variable.COURSE));
                request.setAttribute(Variable.STUDENT, userService.getUserById(studentId, local));
                request.setAttribute(Variable.COURSE, courseService.getCourseById(courseId, local));
                request.getRequestDispatcher(JspPath.REJECT_APPLY_PAGE).forward(request, response);
            }
        } catch (AuthenticationException e) {
            LOGGER.warn("Unauthenticated user tried to access the page " + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + JspPath.LOGIN_PAGE);
        } catch (AuthorizationException e) {
            LOGGER.warn("User " + user.getId() + " tried to access the page " + request.getRequestURI());
            response.sendError(404);
        } catch (NumberFormatException | ServiceException e) {
            LOGGER.error(e.getMessage());
            response.sendError(500);
        }
    }
}

