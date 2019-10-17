package by.trjava.pashkovich.facultative.controller.command.impl;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class ViewSearchCourseResultCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseTitle = request.getParameter(Variable.TITLE);
        String url = "/mainController?command=" + CommandVariety.SEARCH_COURSE + "&title=" + URLEncoder.encode(courseTitle, "UTF-8");
        response.sendRedirect(request.getContextPath() + url);
    }
}
