package by.trjava.pashkovich.facultative.controller.command.impl.student;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.CourseDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.ClassService;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.MarkService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CurrentCourseCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        ClassService classService = ServiceFactory.getClassService();
        MarkService markService = ServiceFactory.getMarkService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        try {
            int courseId = new Integer(request.getParameter(Variable.ID));
            request.setAttribute(Variable.COURSE, courseService.getCourseById(courseId));
            request.setAttribute(Variable.CLASSES_COUNT, classService.getClassesCountByCourse(courseId));
            request.setAttribute(Variable.MARK, markService.getStudentAverageMarkByCourse(user.getId(), courseId));
            request.setAttribute(Variable.MARKS, markService.getStudentMarkWithWorkTitleByCourse(user.getId(), courseId));
            request.getRequestDispatcher(JspPath.COURSE_STUDENT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            response.sendError(500);
        }
    }
}
