package by.trjava.pashkovich.facultative.controller.command.impl.admin;

import by.trjava.pashkovich.facultative.constants.JspPath;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;
import by.trjava.pashkovich.facultative.service.CategoryService;
import by.trjava.pashkovich.facultative.service.ServiceFactory;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAllCategoryCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ShowAllCategoryCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService categoryService = ServiceFactory.getCategoryService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Variable.USER);
        String local = (String) session.getAttribute(Variable.LOCAL);

        if (user == null || user.getRole() != UserRole.ADMINISTRATOR) {
            response.sendError(404);
        } else {
            try {
                request.setAttribute(Variable.CATEGORIES, categoryService.getAllCategoryWithCourseCount(local));
                request.getRequestDispatcher(JspPath.ADMIN_CATEGORY).forward(request, response);
            } catch (ServiceException e) {
                LOGGER.error("Exception to show category for administrator: " + e.getMessage());
                response.sendError(500);
            }
        }

    }
}
