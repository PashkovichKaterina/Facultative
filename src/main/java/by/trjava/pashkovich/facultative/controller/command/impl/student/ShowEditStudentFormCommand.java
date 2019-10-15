package by.trjava.pashkovich.facultative.controller.command.impl.student;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowEditStudentFormCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute(Variable.USER);
        request.setAttribute(Variable.SURNAME, student.getSurname());
        request.setAttribute(Variable.NAME, student.getName());
        request.setAttribute(Variable.PATRONYMIC, student.getPatronymic());
        request.setAttribute(Variable.PHONE, student.getPhone());
        request.setAttribute(Variable.DATE, student.getDateOfBirthString());
        request.setAttribute(Variable.ADDRESS, student.getAddress());
        request.getRequestDispatcher(JspPath.EDIT_STUDENT_PAGE).forward(request, response);
    }
}
