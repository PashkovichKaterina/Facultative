package by.trjava.pashkovich.facultative.controller.command.impl.student;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthorizationException;
import by.trjava.pashkovich.facultative.controller.command.validation.UserRoleValidator;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.util.CustomFormatForDate;
import by.trjava.pashkovich.facultative.entity.Student;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowEditStudentFormCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowEditStudentFormCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);

        try {
            if (UserRoleValidator.isStudentLoggedIn(user)) {
                Student student = (Student) user;
                request.setAttribute(Variable.SURNAME, student.getSurname());
                request.setAttribute(Variable.NAME, student.getName());
                request.setAttribute(Variable.PATRONYMIC, student.getPatronymic());
                request.setAttribute(Variable.PHONE, student.getPhone());
                request.setAttribute(Variable.DATE, CustomFormatForDate.getUseServerDateFormat(student.getDateOfBirth()));
                request.setAttribute(Variable.ADDRESS, student.getAddress());
                request.getRequestDispatcher(JspPath.EDIT_STUDENT_PAGE).forward(request, response);
            }
        } catch (AuthenticationException e) {
            LOGGER.warn("Unauthenticated user tried to access the page " + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + JspPath.LOGIN_PAGE);
        } catch (AuthorizationException e) {
            LOGGER.warn("Student " + user.getId() + " tried to access the page " + request.getRequestURI());
            response.sendError(404);
        }
    }
}