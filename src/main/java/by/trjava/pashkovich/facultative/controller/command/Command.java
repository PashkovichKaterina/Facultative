package by.trjava.pashkovich.facultative.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}