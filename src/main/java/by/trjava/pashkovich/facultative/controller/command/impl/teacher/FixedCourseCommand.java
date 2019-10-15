package by.trjava.pashkovich.facultative.controller.command.impl.teacher;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.transition.PageTransited;
import by.trjava.pashkovich.facultative.service.ClassService;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.WorkService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FixedCourseCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        ClassService classService = ServiceFactory.getClassService();
        WorkService workService = ServiceFactory.getWorkService();

        try {
            int courseId = new Integer(request.getParameter(Variable.ID));
            Course course = courseService.getCourseById(courseId);
            request.setAttribute(Variable.COURSE, course);
            request.setAttribute(Variable.CLASSES, classService.getClassDateTimeByCourse(courseId));
            request.setAttribute(Variable.WORKS, workService.getWorkTitleByCourse(courseId));
            PageTransited.jump(JspPath.COURSE_TEACHER_PAGE, request, response);
        } catch (ServiceException e) {
            response.sendError(500);
        }
    }
}
