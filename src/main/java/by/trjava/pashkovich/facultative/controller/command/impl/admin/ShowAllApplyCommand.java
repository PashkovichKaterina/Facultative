package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAllApplyCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowAllApplyCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplyService applyService = ServiceFactory.getApplyService();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);
        try {
            request.setAttribute(Variable.APPLIES, applyService.getAllApply(local));
            request.getRequestDispatcher(JspPath.ADMIN_APPLY).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.sendError(500);
        }
    }
}
