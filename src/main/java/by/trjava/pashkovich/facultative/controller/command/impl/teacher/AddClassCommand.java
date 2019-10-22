package by.trjava.pashkovich.facultative.controller.command.impl.teacher;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.impl.AccountCommand;
import by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.ClassService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.AddClassException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddClassCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddClassCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassService classService = ServiceFactory.getClassService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);

        String date = request.getParameter(Variable.DATE);
        String time = request.getParameter(Variable.TIME);
        try {
            int courseId = new Integer(request.getParameter(Variable.ID));
            classService.addClass(date, time, courseId, user.getId(), request);
            response.sendRedirect(request.getContextPath() + "/mainController?command=" + CommandVariety.FIXED_COURSE + "&id=" + courseId);
        } catch (AddClassException e) {
            System.out.println(e.getMessage());
            request.setAttribute(Variable.DATE, date);
            request.setAttribute(Variable.TIME, time);
            request.getRequestDispatcher(JspPath.ADD_CLASS_PAGE).forward(request, response);
            LOGGER.info("Unsuccessful to add class by teacher " + user.getLogin() + ": " + e.getMessage());
        } catch (NumberFormatException | ServiceException e) {
            LOGGER.error("Error to add class by teacher " + user.getLogin() + ": " + e.getMessage());
            response.sendError(500);
        }
    }
}
