package by.trjava.pashkovich.facultative.controller.command.impl.student;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthorizationException;
import by.trjava.pashkovich.facultative.controller.command.validation.UserRoleValidator;
import by.trjava.pashkovich.facultative.controller.command.provider.CommandVariety;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.UserService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command is used to edit account information by student.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class EditStudentCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(EditStudentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        try {
            if (UserRoleValidator.isUserLoggedIn(user) && UserRoleValidator.isStudentLoggedIn(user)) {
                userService.editStudent(request);
                response.sendRedirect(request.getContextPath() + "/mainController?command=" + CommandVariety.ACCOUNT);
            }
        } catch (AuthenticationException e) {
            LOGGER.warn("Unauthenticated user tried to access the page " + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + JspPath.LOGIN_PAGE);
        } catch (AuthorizationException e) {
            LOGGER.warn("Student " + user.getId() + " tried to access the page " + request.getRequestURI());
            response.sendError(404);
        } catch (ServiceException e) {
            LOGGER.info("Unsuccessful to edit student data: " + e.getMessage());
            request.getRequestDispatcher(JspPath.EDIT_STUDENT_PAGE).forward(request, response);
        }
    }
}
