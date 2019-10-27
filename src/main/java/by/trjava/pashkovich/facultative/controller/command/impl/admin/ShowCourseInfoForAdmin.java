package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.impl.teacher.EditStudentMarkCommand;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.service.*;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowCourseInfoForAdmin implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowCourseInfoForAdmin.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        ClassService classService = ServiceFactory.getClassService();
        MarkService markService = ServiceFactory.getMarkService();
        WorkService workService = ServiceFactory.getWorkService();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);
        try {
            int courseId = new Integer(request.getParameter(Variable.ID));
            Course course = courseService.getCourseById(courseId, local);
            request.setAttribute(Variable.CLASSES, classService.getClassDateTimeByCourse(courseId));
            request.setAttribute(Variable.COURSE, course);
            request.setAttribute(Variable.WORKS, workService.getWorkTitleByCourse(courseId));
            request.setAttribute(Variable.MARKS, markService.getStudentWithMarkByCourse(courseId));
            request.setAttribute(Variable.END_COURSE_BUTTON, courseService.isEndCourseButtonAvailable(courseId));
            request.getRequestDispatcher(JspPath.COURSE_ADMIN_INFO_PAGE).forward(request, response);
        } catch (ServiceException e) {
            response.sendError(500);
            LOGGER.error("Error to show course for admin: " + e.getMessage());
        }
    }
}
