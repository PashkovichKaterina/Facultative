package by.trjava.pashkovich.facultative.controller.command.impl.teacher;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.MarkDAO;
import by.trjava.pashkovich.facultative.dao.WorkDAO;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.MarkService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.WorkService;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.validation.FieldValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class EditStudentMarkCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(EditStudentMarkCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarkService markService = ServiceFactory.getMarkService();
        WorkService workService = ServiceFactory.getWorkService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        String workTitle = request.getParameter(Variable.WORK);
        try {
            int courseId = new Integer(request.getParameter(Variable.ID));
            int workId = workService.getWorkId(courseId, workTitle);

            Enumeration<String> e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String s = e.nextElement();
                if (FieldValidator.isWholeNumber(s)) {
                    int studentId = new Integer(s);
                    int mark = new Integer(request.getParameter(s));
                    markService.updateMark(studentId, workId, mark, user.getId());
                }
            }
            String url = request.getContextPath() + "/mainController?command=" + CommandVariety.FIXED_COURSE + "&id=" + courseId;
            response.sendRedirect(url);
        } catch (NumberFormatException | ServiceException e) {
            response.sendError(500);
            LOGGER.error("Error to edit student mark by teacher " + user.getLogin() + ": " + e.getMessage());
        }
    }
}
