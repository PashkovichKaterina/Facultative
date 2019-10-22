package by.trjava.pashkovich.facultative.controller;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.provider.CommandProvider;
import by.trjava.pashkovich.facultative.dao.exception.ConnectionPoolException;
import by.trjava.pashkovich.facultative.dao.pool.ConnectionPool;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    public void init() throws ServletException {
        ConnectionPool connectionPool = BaseConnectionPool.getInstance();
        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Cannot create Connection Pool: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.trace("Call doGet() method in Controller");
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.trace("Call doPost() method in Controller");
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(Variable.COMMAND);
        LOGGER.trace("Parameter value \"command\" is " + commandName);
        Command command = CommandProvider.getInstance().getCommand(commandName);
        LOGGER.debug("Call execute() method of the class " + command.getClass().getName());
        command.execute(request, response);
        LOGGER.debug("Method execute() finished work");
    }

    @Override
    public void destroy() {
        try {
            BaseConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            LOGGER.info("Connection pool cannot destroy: " + e.getMessage());
        }
    }
}
