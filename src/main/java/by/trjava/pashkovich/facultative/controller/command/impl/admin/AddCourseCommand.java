package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.*;
import by.trjava.pashkovich.facultative.service.exception.AddCourseException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddCourseCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddCourseCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        UserService userService = ServiceFactory.getUserService();
        SkillService skillService = ServiceFactory.getSkillService();
        ClassService classService = ServiceFactory.getClassService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        String local = (String) session.getAttribute(Variable.LOCAL);
        try {
            courseService.insertCourse(request);
            response.sendRedirect(request.getContextPath() + "/mainController?command=" + CommandVariety.SHOW_ALL_COURSE_FOR_ADMIN);
        } catch (AddCourseException e) {
            LOGGER.info("Incorrect data to add course");
            try {
                request.setAttribute(Variable.CATEGORIES, courseService.getAllCategory(local));
                request.setAttribute(Variable.TEACHERS, userService.getAllTeacher(local));
                request.setAttribute(Variable.SKILLS, skillService.getAllSkill(local));
                request.setAttribute(Variable.LEVELS, skillService.getAllSkillLevel(local));
                request.setAttribute(Variable.DAYS, classService.getAllDays(local));
                request.getRequestDispatcher(JspPath.ADD_COURSE_PAGE).forward(request, response);
            } catch (ServiceException ex) {

            }
        } catch (ServiceException e) {
            LOGGER.warn("Exception to add course: " + e.getMessage());
            response.sendError(500);
        }
    }


}
