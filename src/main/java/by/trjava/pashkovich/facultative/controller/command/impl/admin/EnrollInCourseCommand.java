package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.provider.CommandVariety;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.InvalidDataTypeException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command is used to enroll student in course by admin.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class EnrollInCourseCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(EnrollInCourseCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplyService applyService = ServiceFactory.getApplyService();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);

        for (Map.Entry<Integer, Integer> map : getParameter(request).entrySet()) {
            try {
                applyService.enrollInCourse(map.getKey(), map.getValue());
            } catch (InvalidDataTypeException e) {
                LOGGER.error(e.getMessage());
            } catch (ServiceException e) {
                LOGGER.warn(e.getMessage());
                response.sendError(500);
                return;
            }
        }
        try {
            request.setAttribute(Variable.APPLIES, applyService.getAllApply(local));
            response.sendRedirect(request.getContextPath() + "/mainController?command=" + CommandVariety.SHOW_ALL_APPLY);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage());
            response.sendError(500);
        }
    }

    private Map<Integer, Integer> getParameter(HttpServletRequest request) {
        Map<Integer, Integer> parameter = new HashMap<>();
        Pattern pattern = Pattern.compile("^\\d+\\-\\d+$");
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String element = enumeration.nextElement();
            Matcher matcher = pattern.matcher(element);
            if (matcher.matches()) {
                String[] param = element.split("-");
                int studentId = new Integer(param[0]);
                int courseId = new Integer(param[1]);
                parameter.put(studentId, courseId);
            }
        }
        return parameter;
    }
}
