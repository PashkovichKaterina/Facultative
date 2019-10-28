package by.trjava.pashkovich.facultative.controller.command.impl.teacher;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.controller.command.validation.UserRoleValidator;
import by.trjava.pashkovich.facultative.controller.command.provider.CommandVariety;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.WorkService;
import by.trjava.pashkovich.facultative.service.exception.EmptyDataException;
import by.trjava.pashkovich.facultative.service.exception.InvalidDataTypeException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.exception.WorkTitleIsAlreadyContainedException;
import by.trjava.pashkovich.facultative.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command is used to add work on course by teacher.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class AddWorkCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddWorkCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WorkService workService = ServiceFactory.getWorkService();
        MessageManager messageManager = MessageManager.getInstance();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(Variable.USER);
        String local = (String) session.getAttribute(Variable.LOCAL);
        String workTitle = request.getParameter(Variable.TITLE);
        request.setAttribute(Variable.TITLE, workTitle);
        try {
            UserRoleValidator.isUserLoggedIn(user);
            int courseId = new Integer(request.getParameter(Variable.ID));
            workService.addWork(workTitle, courseId, user.getId());
            response.sendRedirect(request.getContextPath() + "/mainController?command=" + CommandVariety.FIXED_COURSE + "&id=" + courseId);
        } catch (EmptyDataException e) {
            request.setAttribute(Variable.TITLE_ERROR, messageManager.getMessage(InformMessage.REQUIRE_FIELD, local));
            request.getRequestDispatcher(JspPath.ADD_WORK_PAGE).forward(request, response);
            LOGGER.debug("Invalid data to add work: " + e.getMessage());
        } catch (WorkTitleIsAlreadyContainedException e) {
            request.setAttribute(Variable.TITLE_ERROR, messageManager.getMessage(InformMessage.EXIST_WORK_TITLE, local));
            request.getRequestDispatcher(JspPath.ADD_WORK_PAGE).forward(request, response);
            LOGGER.debug("Invalid data format to add work: " + e.getMessage());
        } catch (InvalidDataTypeException e) {
            request.setAttribute(Variable.TITLE_ERROR, messageManager.getMessage(InformMessage.TEXT_FIELD_INVALID_TYPE, local));
            request.getRequestDispatcher(JspPath.ADD_WORK_PAGE).forward(request, response);
            LOGGER.debug("Invalid data type to add work: " + e.getMessage());
        } catch (AuthenticationException e) {
            LOGGER.warn("Unauthenticated user tried to access the page " + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + JspPath.LOGIN_PAGE);
        } catch (NumberFormatException | ServiceException e) {
            response.sendError(500);
            LOGGER.error("Error to add work by teacher " + user.getLogin() + ": " + e.getMessage());
        }
    }
}
