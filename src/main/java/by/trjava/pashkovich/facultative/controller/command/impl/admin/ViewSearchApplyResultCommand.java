package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class ViewSearchApplyResultCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseTitle = request.getParameter(Variable.COURSE);
        String studentName = request.getParameter(Variable.NAME);
        String url = null;
        if (studentName != null) {
            url = "/mainController?command=" + CommandVariety.SEARCH_APPLY + "&name=" + URLEncoder.encode(studentName, "UTF-8");
        }
        if (courseTitle != null) {
            url = "/mainController?command=" + CommandVariety.SEARCH_APPLY + "&course=" + URLEncoder.encode(courseTitle, "UTF-8");
        }
        response.sendRedirect(request.getContextPath() + url);
    }
}
