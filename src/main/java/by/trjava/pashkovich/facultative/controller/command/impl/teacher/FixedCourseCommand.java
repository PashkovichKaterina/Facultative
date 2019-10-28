package by.trjava.pashkovich.facultative.controller.command.impl.teacher;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.controller.command.validation.UserRoleValidator;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.*;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command is used to show course information for teacher which is fixed behind him.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class FixedCourseCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(FixedCourseCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        ClassService classService = ServiceFactory.getClassService();
        WorkService workService = ServiceFactory.getWorkService();
        MarkService markService = ServiceFactory.getMarkService();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);
        User user = (User) session.getAttribute(Variable.USER);
        try {
            UserRoleValidator.isUserLoggedIn(user);
            int courseId = new Integer(request.getParameter(Variable.ID));
            Course course = courseService.getCourseById(courseId, local);
            request.setAttribute(Variable.COURSE, course);
            request.setAttribute(Variable.CLASSES, classService.getClassDateTimeByCourse(courseId));
            request.setAttribute(Variable.WORKS, workService.getWorkTitleByCourse(courseId));
            request.setAttribute(Variable.MARKS, markService.getStudentWithMarkByCourse(courseId));
            request.setAttribute(Variable.END_COURSE_BUTTON, courseService.isEndCourseButtonAvailable(courseId));
            request.getRequestDispatcher(JspPath.COURSE_TEACHER_PAGE).forward(request, response);
        } catch (AuthenticationException e) {
            LOGGER.warn("Unauthenticated user tried to access the page " + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + JspPath.LOGIN_PAGE);
        } catch (ServiceException e) {
            response.sendError(500);
            LOGGER.error("Error to show fixed course for teacher: " + e.getMessage());
        }
    }
}
