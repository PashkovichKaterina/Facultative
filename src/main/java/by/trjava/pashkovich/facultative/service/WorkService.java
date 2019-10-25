package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Set;

public interface WorkService {
    /**
     * Returns a set of all works title at the specified course.
     *
     * @param courseId course id.
     * @return a set of all works title at the specified course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<String> getWorkTitleByCourse(int courseId) throws ServiceException;

    /**
     * Adds work with the work title at the specified course if possible.
     *
     * <p>You can add work if the specified course is fixed for the teacher and work with such a title
     * for this course has not been previously added.</p>
     *
     * @param workTitle work title.
     * @param courseId  course id.
     * @param teacherId teacher id.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void addWork(String workTitle, int courseId, int teacherId) throws ServiceException;

    /**
     * Returns the work id at the specified course and work title.
     *
     * @param courseId course id.
     * @param title    work title.
     * @return the id of the work at the specified course and work title.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    int getWorkId(int courseId, String title) throws ServiceException;
}
