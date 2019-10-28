package by.trjava.pashkovich.facultative.controller.command.impl;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.provider.CommandVariety;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Command is used to show course by search results for user.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class ViewSearchCourseResultCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ViewSearchCourseResultCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseTitle = request.getParameter(Variable.TITLE);
        String url = "/mainController?command=" + CommandVariety.SEARCH_COURSE + "&title=" + URLEncoder.encode(courseTitle, "UTF-8");
        response.sendRedirect(request.getContextPath() + url);
        LOGGER.debug("Send redirect for search course by title");
    }
}
