package by.trjava.pashkovich.facultative.controller.command.impl;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety;
import by.trjava.pashkovich.facultative.entity.*;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.ArchiveService;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AccountCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArchiveService archiveService = ServiceFactory.getArchiveService();
        ApplyService applyService = ServiceFactory.getApplyService();
        CourseService courseService = ServiceFactory.getCourseService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        String local = (String) session.getAttribute(Variable.LOCAL);
        try {
            switch (user.getRole()) {
                case ADMINISTRATOR:
                    response.sendRedirect(request.getContextPath() + "/mainController?command=" + CommandVariety.SHOW_ALL_SKILL);
                    break;
                case STUDENT:
                    request.setAttribute(Variable.ARCHIVE, archiveService.getArchiveCourseByStudent(user.getId(), local));
                    request.setAttribute(Variable.APPLY, applyService.getApplyByStudent(user.getId(), local));
                    request.setAttribute(Variable.CURRENT_COURSE, courseService.getStudentCurrentCourse(user.getId(), local));
                    request.getRequestDispatcher(JspPath.ACCOUNT_STUDENT_PAGE).forward(request, response);
                    LOGGER.debug("Account page for student: " + user.getLogin());
                    break;
                case TEACHER:
                    request.setAttribute(Variable.COURSES, courseService.getCourseWithStatusByTeacher(user.getId(), local));
                    request.getRequestDispatcher(JspPath.ACCOUNT_TEACHER_PAGE).forward(request, response);
                    LOGGER.debug("Account page for teacher: " + user.getLogin());
                    break;
            }
        } catch (ServiceException e) {
            LOGGER.error("Unsuccessful redirect to account page: " + e.getMessage());
            response.sendError(500);
        }
    }
}
