package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;

import java.util.Date;
import java.util.Set;

public interface ClassDAO {
    /**
     * Inserts class for specific course.
     *
     * @param courseId course id.
     * @param date     class date.
     * @param time     class time.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertClass(int courseId, String date, String time) throws DAOException;

    /**
     * Checks if at this course there is already a lesson on the specified date.
     *
     * @param courseId course id.
     * @param date     class date.
     * @return {@code true} if at this course there is already a lesson on the specified date,
     * {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    boolean isContains(int courseId, String date) throws DAOException;

    /**
     * Delete all class by specific course.
     *
     * @param courseId course id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void deleteClassByCourse(int courseId) throws DAOException;

    /**
     * Returns classes count by specific course.
     *
     * @param courseId course id.
     * @return classes count by specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    int getClassesCountByCourse(int courseId) throws DAOException;

    /**
     * Returns returns the date and time of all classes at the specified course.
     *
     * @param courseId course id.
     * @return the date and time of all classes at the specified course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Date> getClassDateTimeByCourse(int courseId) throws DAOException;

    /**
     * Returns all days in Russian on which classes from the database can be held.
     *
     * @return all days in Russian on which classes from the database can be held.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<String> getAllDaysOnRu() throws DAOException;

    /**
     * Returns all days in English on which classes from the database can be held.
     *
     * @return all days in English on which classes from the database can be held.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<String> getAllDaysOnEn() throws DAOException;

    /**
     * Returns begin date of the specific course.
     *
     * @param courseId course id.
     * @return begin date of the specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Date getBeginDateByCourse(int courseId) throws DAOException;

    /**
     * Returns end date of the specific course.
     *
     * @param courseId course id.
     * @return end date of the specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Date getEndDateByCourse(int courseId) throws DAOException;
}
