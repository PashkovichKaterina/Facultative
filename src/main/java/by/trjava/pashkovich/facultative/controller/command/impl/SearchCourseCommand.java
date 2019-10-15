package by.trjava.pashkovich.facultative.controller.command.impl;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.NoSuchCategoryException;
import by.trjava.pashkovich.facultative.service.exception.NoSuchCourseException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchCourseCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        String courseTitle = request.getParameter(Variable.TITLE);
        String category = request.getParameter(Variable.CATEGORY);
        try {
            request.setAttribute(Variable.CATEGORIES, courseService.getAllCategory());
            request.setAttribute(Variable.COURSES, courseService.searchCourse(courseTitle, category));
            request.getRequestDispatcher(JspPath.COURSE_PAGE).forward(request, response);
        } catch (NoSuchCourseException | NoSuchCategoryException e) {
            request.setAttribute(Variable.INFORM_MESSAGE, messageManager.getMessage(InformMessage.NO_SUCH_COURSE, local));
            request.getRequestDispatcher(JspPath.COURSE_PAGE).forward(request, response);
        } catch (ServiceException e) {
            response.sendError(505);
        }
    }
}
