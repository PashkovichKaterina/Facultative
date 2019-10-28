package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
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
 * Command is used to enroll student in learning by admin.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Command
 * @since JDK1.0
 */
public class EnrollInLearningCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(EnrollInLearningCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplyService applyService = ServiceFactory.getApplyService();
        HttpSession session = request.getSession();
        String local = (String) session.getAttribute(Variable.LOCAL);
        MessageManager messageManager = MessageManager.getInstance();

        request.setAttribute(Variable.INFORM_MESSAGE, messageManager.getMessage(InformMessage.SUCCESSFULLY_OPERATION, local));
        for (Map.Entry<Integer, Integer> map : getParameter(request).entrySet()) {
            try {
                applyService.enrollInLearning(map.getKey(), map.getValue());
            } catch (InvalidDataTypeException e) {
                LOGGER.error(e.getMessage());
                request.setAttribute(Variable.INFORM_MESSAGE, messageManager.getMessage(InformMessage.UNSUCCESSFULLY_OPERATION, local));
            } catch (ServiceException e) {
                LOGGER.warn(e.getMessage());
                response.sendError(500);
                return;
            }
        }
        try {
            request.setAttribute(Variable.APPLIES, applyService.getAllApply(local));
            request.getRequestDispatcher(JspPath.ADMIN_APPLY).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage());
            response.sendError(500);
        }
    }

    private Map<Integer, Integer> getParameter(HttpServletRequest request) {
        Map<Integer, Integer> parameter = new HashMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        Pattern pattern = Pattern.compile("^\\d+\\-\\d+$");
        while (enumeration.hasMoreElements()) {
            String param = enumeration.nextElement();
            Matcher matcher = pattern.matcher(param);
            if (matcher.matches()) {
                String[] element = param.split("-");
                int courseId = new Integer(element[1]);
                int studentId = new Integer(element[0]);
                parameter.put(studentId, courseId);
            }
        }
        return parameter;
    }
}
