package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
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
 * Command is used to show information about user to admin.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class ShowUserAccountCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowUserAccountCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = ServiceFactory.getCourseService();
        ArchiveService archiveService = ServiceFactory.getArchiveService();
        ApplyService applyService = ServiceFactory.getApplyService();
        UserService userService = ServiceFactory.getUserService();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);
        try {
            int userId = new Integer(request.getParameter(Variable.ID));
            User user = userService.getUserById(userId, local);
            switch (user.getRole()) {
                case STUDENT:
                    request.setAttribute(Variable.REVIEW, archiveService.getAllCourseWithReviewByStudent(userId, local));
                    request.setAttribute(Variable.STUDENT, user);
                    request.setAttribute(Variable.ARCHIVE, archiveService.getArchiveCourseWithMarkByStudent(userId, local));
                    request.setAttribute(Variable.APPLY, applyService.getApplyByStudent(user.getId(), local));
                    request.setAttribute(Variable.CURRENT_COURSE, courseService.getStudentCurrentCourse(user.getId(), local));
                    request.getRequestDispatcher(JspPath.STUDENT_INFO_PAGE).forward(request, response);
                    break;
                case TEACHER:
                    request.setAttribute(Variable.TEACHER, user);
                    request.setAttribute(Variable.COURSES, courseService.getCourseWithStatusByTeacher(userId, local));
                    request.getRequestDispatcher(JspPath.TEACHER_INFO_PAGE).forward(request, response);
                    break;
            }
        } catch (NumberFormatException | ServiceException e) {
            response.sendError(500);
            LOGGER.error(e.getMessage());
        }
    }
}
