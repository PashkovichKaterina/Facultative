package by.trjava.pashkovich.facultative.controller.command.impl;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewCourseInfoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        ApplyService applyService = ServiceFactory.getApplyService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        int courseId = new Integer(request.getParameter(Variable.ID));

        try {
            request.setAttribute(Variable.COURSE, courseService.getCourseById(courseId));
            request.setAttribute(Variable.REQUIREMENT, courseService.getCourseRequirement(courseId));
            request.setAttribute(Variable.TIMETABLE, courseService.getCourseTimetable(courseId));
            request.setAttribute(Variable.APPLY_BUTTON, applyService.isAvailableApplyButton(courseId, user));
            request.getRequestDispatcher(JspPath.COURSE_INFO_PAGE).forward(request, response);
        } catch (ServiceException e) {
            response.sendError(500);
        }
    }
}
