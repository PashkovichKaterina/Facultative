package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface ClassService {
    int getClassesCountByCourse(int courseId) throws ServiceException;

    void addClass(String date, String time, int courseId, int teacherId, HttpServletRequest request) throws ServiceException;

    Set<Date> getClassDateTimeByCourse(int courseId) throws ServiceException;

    Set<String> getAllDays(String local) throws ServiceException;
}
