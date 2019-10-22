package by.trjava.pashkovich.facultative.controller.command.impl.teacher;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.MarkService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowEditMarkFormCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowEditMarkFormCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        MarkService markService = ServiceFactory.getMarkService();
        HttpSession session = request.getSession();
        String workTitle = request.getParameter(Variable.WORK);
        String local = (String) session.getAttribute(Variable.LOCAL);
        try {
            int courseId = new Integer(request.getParameter(Variable.ID));
            request.setAttribute(Variable.COURSE, courseService.getCourseById(courseId, local));
            request.setAttribute(Variable.WORK, workTitle);
            request.setAttribute(Variable.STUDENTS, markService.getStudentWithMarkByCourseWork(courseId, workTitle));
            request.getRequestDispatcher(JspPath.COURSE_TEACHER_EDIT_PAGE).forward(request, response);
        } catch (NumberFormatException | ServiceException e) {
            response.sendError(500);
            LOGGER.error("Error to show edit mark form: " + e.getMessage());
        }
    }
}
