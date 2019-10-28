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
import by.trjava.pashkovich.facultative.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command is used to show course by user.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class SearchCourseCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SearchCourseCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        MessageManager messageManager = MessageManager.getInstance();
        String local = (String) request.getSession().getAttribute(Variable.LOCAL);
        String courseTitle = request.getParameter(Variable.TITLE);
        String category = request.getParameter(Variable.CATEGORY);
        LOGGER.debug("Search courses with title = " + courseTitle + " and category = " + category);
        try {
            request.setAttribute(Variable.CATEGORIES, courseService.getAllCategory(local));
            request.setAttribute(Variable.COURSES, courseService.searchCourse(courseTitle, category, local));
            request.setAttribute(Variable.TITLE, courseTitle);
            request.getRequestDispatcher(JspPath.COURSE_PAGE).forward(request, response);
        } catch (NoSuchCourseException | NoSuchCategoryException e) {
            LOGGER.debug("Unsuccessful course search: " + e.getMessage());
            request.setAttribute(Variable.INFORM_MESSAGE, messageManager.getMessage(InformMessage.NO_SUCH_COURSE, local));
            request.getRequestDispatcher(JspPath.COURSE_PAGE).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.error("Error when searching course: " + e.getMessage());
            response.sendError(505);
        }
    }
}
