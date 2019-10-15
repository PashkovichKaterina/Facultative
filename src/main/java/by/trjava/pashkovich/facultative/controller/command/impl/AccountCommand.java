package by.trjava.pashkovich.facultative.controller.command.impl;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.entity.*;
import by.trjava.pashkovich.facultative.entity.characteristics.UserRole;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.ArchiveService;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArchiveService archiveService = ServiceFactory.getArchiveService();
        ApplyService applyService = ServiceFactory.getApplyService();
        CourseService courseService = ServiceFactory.getCourseService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        try {
            if (user.getRole() == UserRole.STUDENT) {
                request.setAttribute(Variable.ARCHIVE, archiveService.getArchiveCourseByStudent(user.getId()));
                request.setAttribute(Variable.APPLY, applyService.getApplyByStudent((Student) user));
                request.setAttribute(Variable.CURRENT_COURSE, courseService.getStudentCurrentCourse(user.getId()));
                request.getRequestDispatcher(JspPath.ACCOUNT_STUDENT_PAGE).forward(request, response);
            } else {
                request.setAttribute(Variable.COURSES, courseService.getCourseByTeacher(user.getId()));
                request.getRequestDispatcher(JspPath.ACCOUNT_TEACHER_PAGE).forward(request, response);
            }
        } catch (ServiceException e) {
            response.sendError(500);
        }
    }

}
