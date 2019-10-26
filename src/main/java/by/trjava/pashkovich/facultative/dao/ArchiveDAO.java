package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;

import java.util.Date;
import java.util.Map;

public interface ArchiveDAO {
    /**
     * Inserts information about the course taken by the student.
     *
     * @param studentId   student id.
     * @param courseId    course id.
     * @param averageMark student average mark.
     * @param startDate   course start date.
     * @param endDate     course end date.
     * @param review      review about student work.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertArchive(int studentId, int courseId, int averageMark, Date startDate, Date endDate, String review) throws DAOException;

    /**
     * Inserts information about the course taken by the student.
     *
     * @param studentId   student id.
     * @param courseId    course id.
     * @param averageMark student average mark.
     * @param startDate   course start date.
     * @param endDate     course end date.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void updateArchive(int studentId, int courseId, int averageMark, Date startDate, Date endDate) throws DAOException;

    /**
     * Update review about student work on specific course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @param review    review about student work.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void updateReview(int studentId, int courseId, String review) throws DAOException;

    /**
     * Checks if the archive contains information about the specified student
     * at the specified course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return {@code true} if the archive contains information about the specified student
     * at the specified course, {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    boolean isContains(int studentId, int courseId) throws DAOException;

    /**
     * Returns review about specific student by specific course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return review about specific student by specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    String getReview(int studentId, int courseId) throws DAOException;

    /**
     * Returns all archive course on Russian language with average mark for specific student.
     *
     * @param studentId student id.
     * @return all archive course on Russian language with average mark for specific student.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudentOnRu(int studentId) throws DAOException;

    /**
     * Returns all archive course on English language with average mark for specific student.
     *
     * @param studentId student id.
     * @return all archive course on English language with average mark for specific student.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudentOnEn(int studentId) throws DAOException;
}
