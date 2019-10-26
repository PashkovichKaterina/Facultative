package by.trjava.pashkovich.facultative.dao;


import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Student;

import java.util.List;
import java.util.Map;

public interface MarkDAO {
    /**
     * Inserts the grade to the specified student for the specified work in the database.
     *
     * @param studentId student id.
     * @param workId    work id.
     * @param mark      work mark.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertMark(int studentId, int workId, int mark) throws DAOException;

    /**
     * Updates the grade to the specified student for the specified work in the database.
     *
     * @param studentId student id.
     * @param workId    work id.
     * @param mark      work mark.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void updateMark(int studentId, int workId, int mark) throws DAOException;

    /**
     * Deletes all marks for the specified course.
     *
     * @param courseId course id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void deleteMarkByCourse(int courseId) throws DAOException;

    /**
     * Returns the average mark of the specified student at the specified course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return the average mark of the specified student at the specified course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    int getStudentAverageMarkByCourse(int studentId, int courseId) throws DAOException;

    /**
     * Returns all student mark in the format work title-mark.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return all student mark in the format work title-mark.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    List<Integer> getStudentMarkByCourse(int studentId, int courseId) throws DAOException;

    /**
     * List of all student marks in the order they are added to the database.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return a list of all student marks in the order they are added to the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<String, Integer> getStudentMarkWithWorkTitleByCourse(int studentId, int courseId) throws DAOException;

    /**
     * All students with an average mark at the specific course.
     *
     * @param courseId  course id.
     * @param workTitle work title.
     * @return all students with an average mark at the specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<Student, Integer> getStudentWithMarkByCourseWork(int courseId, String workTitle) throws DAOException;
}
