package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Set;

public interface ClassService {
    /**
     * Returns the number of classes at a specific course.
     *
     * @param courseId course id.
     * @return the number of classes at a specific course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    int getClassesCountByCourse(int courseId) throws ServiceException;

    /**
     * Adds a class with the specified date and time to the specified course if possible.
     *
     * <p>You can add a lesson to the course if the course is fixed for the specific teacher
     * and no classes were held on the specified day.</p>
     *
     * @param date      class date.
     * @param time      class time.
     * @param courseId  course id.
     * @param teacherId teacher id.
     * @param request   the {@code HttpServletRequest} object.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void addClass(String date, String time, int courseId, int teacherId, HttpServletRequest request) throws ServiceException;

    /**
     * Returns the date and time of all classes that were held at a specific course.
     *
     * @param courseId course id.
     * @return the date and time of all classes that were held at a specific course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<Date> getClassDateTimeByCourse(int courseId) throws ServiceException;

    /**
     * Returns a set of all days of the week from the database on which classes can be held.
     *
     * @param local language used by the user.
     * @return a set of all days of the week from the database on which classes can be held.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<String> getAllDays(String local) throws ServiceException;
}
