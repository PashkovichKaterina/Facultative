package by.trjava.pashkovich.facultative.controller.command.impl.teacher;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthorizationException;
import by.trjava.pashkovich.facultative.controller.command.validation.UserRoleValidator;
import by.trjava.pashkovich.facultative.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command is used to show add work form for teacher.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class ShowAddWorkFormCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowAddClassFormCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        try {
            if (UserRoleValidator.isUserLoggedIn(user) && UserRoleValidator.isTeacherLoggedIn(user)) {
                request.getRequestDispatcher(JspPath.ADD_WORK_PAGE).forward(request, response);
            }
        } catch (AuthorizationException e) {
            LOGGER.warn("User " + user.getId() + " tried to access the page " + request.getRequestURI());
            response.sendError(404);
        } catch (AuthenticationException e) {
            response.sendRedirect(request.getContextPath() + JspPath.LOGIN_PAGE);
            LOGGER.warn("Unauthenticated user tried to access the page " + request.getRequestURI());
        }
    }
}
