package by.trjava.pashkovich.facultative.controller.command.impl.student;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.NotLoginException;
import by.trjava.pashkovich.facultative.service.exception.RequiredAccountInformationNotEnteredException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.exception.UnavailableOperationException;
import by.trjava.pashkovich.facultative.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ApplyCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ApplyCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ApplyService applyService = ServiceFactory.getApplyService();
        MessageManager messageManager = MessageManager.getInstance();

        User user = (User) session.getAttribute(Variable.USER);
        int courseId = new Integer(request.getParameter(Variable.ID));
        String local = (String) session.getAttribute(Variable.LOCAL);
        String informMessage = InformMessage.SUCCESS_APPLY;
        try {
            applyService.insertApply(courseId, user);
        } catch (NotLoginException e) {
            informMessage = InformMessage.NOT_LOGIN;
            LOGGER.debug("Attempt to apply by unregistered user");
        } catch (RequiredAccountInformationNotEnteredException e) {
            informMessage = InformMessage.ENTER_ACCOUNT_DATA;
            LOGGER.debug("Attempt to apply by user without required account information");
        } catch (UnavailableOperationException e) {
            informMessage = InformMessage.UNAVAILABLE_OPERATION;
            LOGGER.warn("Unavailable operation \"Apply\"");
        } catch (ServiceException e) {
            response.sendError(500);
            LOGGER.error("Unsuccessful apply: " + e.getMessage());
        } finally {
            request.setAttribute(Variable.INFORM_MESSAGE, messageManager.getMessage(informMessage, local));
            String url = "mainController?command=" + CommandVariety.VIEW_COURSE_INFO + "&id=" + courseId;
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
