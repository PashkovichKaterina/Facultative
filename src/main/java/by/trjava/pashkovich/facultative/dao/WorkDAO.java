package by.trjava.pashkovich.facultative.dao;


import by.trjava.pashkovich.facultative.dao.exception.DAOException;

import java.util.Set;

public interface WorkDAO {
    /**
     * Inserts work with the specified title at the specified course into the database.
     *
     * @param courseId course id.
     * @param title    work title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertWork(int courseId, String title) throws DAOException;

    /**
     * Delete all works from a specific course.
     *
     * @param courseId course id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void deleteWorkByCourse(int courseId) throws DAOException;

    /**
     * Returns all work title from a specific course.
     *
     * @param courseId course id.
     * @return all work title from a specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<String> getWorkTitleByCourse(int courseId) throws DAOException;

    /**
     * Returns work id which title equals with the specific.
     *
     * @param courseId course id.
     * @param title    work title.
     * @return work id which title equals with the specific.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    int getWorkId(int courseId, String title) throws DAOException;

    /**
     * Checks if the specified work is fixed for the specified teacher.
     *
     * @param workId    work id.
     * @param teacherId teacher id.
     * @return {@code true} if the specified work is fixed for the specified teacher, {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    boolean isWorkAvailableForTeacher(int workId, int teacherId) throws DAOException;

    /**
     * Checks if the specified work is available for the specified student.
     *
     * @param workId    work id.
     * @param studentId student id.
     * @return {@code true} if the specified work is available for the specified student,
     * {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    boolean isWorkAvailableForStudent(int workId, int studentId) throws DAOException;
}