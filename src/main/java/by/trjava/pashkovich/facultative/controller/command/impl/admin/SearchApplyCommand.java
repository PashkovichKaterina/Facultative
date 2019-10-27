package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthorizationException;
import by.trjava.pashkovich.facultative.controller.command.validation.UserRoleValidator;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command is used to search apply by admin.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class SearchApplyCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SearchApplyCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplyService applyService = ServiceFactory.getApplyService();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);
        User user = (User) session.getAttribute(Variable.USER);

        String courseTitle = request.getParameter(Variable.COURSE);
        String studentName = request.getParameter(Variable.NAME);

        try {
            if (UserRoleValidator.isAdministratorLoggedIn(user)) {
                request.setAttribute(Variable.APPLIES, applyService.searchApply(studentName, courseTitle, local));
                request.getRequestDispatcher(JspPath.ADMIN_APPLY).forward(request, response);
            }
        } catch (AuthenticationException e) {
            LOGGER.warn("Unauthenticated user tried to access the page " + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + JspPath.LOGIN_PAGE);
        } catch (AuthorizationException e) {
            LOGGER.warn("User " + user.getId() + " tried to access the page " + request.getRequestURI());
            response.sendError(404);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.sendError(500);
        }
    }
}