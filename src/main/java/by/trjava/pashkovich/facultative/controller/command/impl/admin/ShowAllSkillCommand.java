package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.SkillService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAllSkillCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowAllCourseForAdmin.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SkillService skillService = ServiceFactory.getSkillService();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);
        try {
            request.setAttribute(Variable.SKILLS, skillService.getAllSkillWithCourseCount(local));
            request.getRequestDispatcher(JspPath.ADMIN_SKILL).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.error("Exception to show skill for administrator: " + e.getMessage());
            response.sendError(500);
        }
    }
}
