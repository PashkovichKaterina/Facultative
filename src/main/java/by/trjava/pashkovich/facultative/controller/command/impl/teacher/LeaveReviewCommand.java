package by.trjava.pashkovich.facultative.controller.command.impl.teacher;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.controller.command.validation.UserRoleValidator;
import by.trjava.pashkovich.facultative.controller.command.provider.CommandVariety;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.InvalidDataTypeException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command is used to leave review about student by teacher.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class LeaveReviewCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LeaveReviewCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        int studentId = new Integer(request.getParameter(Variable.STUDENT));
        int courseId = new Integer(request.getParameter(Variable.COURSE));
        String review = request.getParameter(Variable.REVIEW);
        ApplyService applyService = ServiceFactory.getApplyService();

        try {
            UserRoleValidator.isUserLoggedIn(user);
            applyService.leaveReview(studentId, courseId, review);
            response.sendRedirect(request.getContextPath() + "/mainController?command=" + CommandVariety.FIXED_COURSE + "&id=" + courseId);
        } catch (AuthenticationException e) {
            LOGGER.warn("Unauthenticated user tried to access the page " + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + JspPath.LOGIN_PAGE);
        } catch (InvalidDataTypeException e) {
            LOGGER.error(e.getMessage());
            response.sendError(500);
        } catch (ServiceException e) {
            LOGGER.warn(e.getMessage());
            response.sendError(500);
        }
    }
}
