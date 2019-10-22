package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.dao.SkillDAO;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;
import by.trjava.pashkovich.facultative.service.*;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAddCourseFormCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowAddCourseFormCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        UserService userService = ServiceFactory.getUserService();
        SkillService skillService = ServiceFactory.getSkillService();
        ClassService classService = ServiceFactory.getClassService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        String local = (String) session.getAttribute(Variable.LOCAL);
        if (user == null || user.getRole() != UserRole.ADMINISTRATOR) {
            response.sendError(404);
        } else {
            try {
                request.setAttribute(Variable.CATEGORIES, courseService.getAllCategory(local));
                request.setAttribute(Variable.TEACHERS, userService.getAllTeacher(local));
                request.setAttribute(Variable.SKILLS, skillService.getAllSkill(local));
                request.setAttribute(Variable.LEVELS, skillService.getAllSkillLevel(local));
                request.setAttribute(Variable.DAYS, classService.getAllDays(local));
                request.getRequestDispatcher(JspPath.ADD_COURSE_PAGE).forward(request, response);
            } catch (ServiceException e) {
                LOGGER.error("Exception to show add course form: " + e.getMessage());
                response.sendError(500);
            }

        }
    }
}