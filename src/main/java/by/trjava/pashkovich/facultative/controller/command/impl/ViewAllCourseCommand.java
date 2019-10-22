package by.trjava.pashkovich.facultative.controller.command.impl;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewAllCourseCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ViewAllCourseCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);

        try {
            request.setAttribute(Variable.COURSES, courseService.getAllCourse(local));
            request.setAttribute(Variable.CATEGORIES, courseService.getAllCategory(local));
            request.getRequestDispatcher(JspPath.COURSE_PAGE).forward(request, response);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            LOGGER.error("Exception when viewing the entire courses: " + e.getMessage());
            response.sendError(500);
        }
    }
}
