package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RejectApplyCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RejectApplyCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = new Integer(request.getParameter(Variable.STUDENT));
        int courseId = new Integer(request.getParameter(Variable.COURSE));
        String review = request.getParameter(Variable.REVIEW);
        ApplyService applyService = ServiceFactory.getApplyService();
        try {
            applyService.rejectApply(studentId, courseId, review);
        } catch (ServiceException e) {
            LOGGER.warn(e.getMessage());
            response.sendError(500);
        }
    }
}
