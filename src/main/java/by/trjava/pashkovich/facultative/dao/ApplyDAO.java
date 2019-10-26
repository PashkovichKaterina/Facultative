package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Course;

import java.util.Map;
import java.util.Set;

public interface ApplyDAO {
    /**
     * Inserts apply specific student on specific course with specific status.
     *
     * @param studentId   user id.
     * @param courseId    course id.
     * @param applyStatus apply status.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertApply(int studentId, int courseId, int applyStatus) throws DAOException;

    /**
     * Updates apply specific student on specific course with specific status.
     *
     * @param studentId   student id.
     * @param courseId    course id.
     * @param applyStatus apply status.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void updateApplyStatus(int studentId, int courseId, int applyStatus) throws DAOException;

    /**
     * Deletes apply by specific student on specific course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void deleteApply(int studentId, int courseId) throws DAOException;

    /**
     * Deletes all applications from the specific course with the status "Learning".
     *
     * @param courseId course id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void deleteAllLearnApplyByCourse(int courseId) throws DAOException;

    /**
     * Checks if the database contains the application of the specified student
     * for the specified course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return {@code true} if the database contains the application of the specified student
     * for the specified course, {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    boolean isContains(int studentId, int courseId) throws DAOException;

    /**
     * Returns all courses on Russian language for which the student applied for together with her status.
     *
     * @param studentId student id.
     * @return all courses for which the student applied for together with her status.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<Course, String> getApplyByStudentOnRu(int studentId) throws DAOException;

    /**
     * Returns all courses on English language for which the student applied for together with her status.
     *
     * @param studentId student id.
     * @return all courses for which the student applied for together with her status.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<Course, String> getApplyByStudentOnEn(int studentId) throws DAOException;

    /**
     * Returns all application on Russian stored in the database.
     *
     * @return all application on Russian stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Apply> getAllApplyOnRu() throws DAOException;

    /**
     * Returns all application on English stored in the database.
     *
     * @return all application on English stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Apply> getAllApplyOnEn() throws DAOException;

    /**
     * Returns all student applications on Russian language by his name.
     *
     * @param studentName student name.
     * @return all student applications by his name.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Apply> getAllApplyByStudentNameOnRu(String studentName) throws DAOException;

    /**
     * Returns all student applications on English language by his name.
     *
     * @param studentName student name.
     * @return all student applications by his name.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Apply> getAllApplyByStudentNameOnEn(String studentName) throws DAOException;

    /**
     * Returns all student applications on Russian language by course title.
     *
     * @param studentName student name.
     * @return all student applications by course title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Apply> getAllApplyByCourseTitleOnRu(String studentName) throws DAOException;

    /**
     * Returns all student applications on English language course title.
     *
     * @param studentName student name.
     * @return all student applications by course title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Apply> getAllApplyByCourseTitleOnEn(String studentName) throws DAOException;
}
