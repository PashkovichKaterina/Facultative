package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;
import java.util.Set;

public interface ApplyService {
    /**
     * Inserts the application of the specified student
     * at the specified rate with the status "Application filed".
     *
     * <p>The user can apply for the course if he has the role {@code UserRole.STUDENT} and filled up
     * required fields of surname, name, patronymic and phone number.</p>
     *
     * @param courseId course id to insert.
     * @param user     user for insert.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void insertApply(int courseId, User user) throws ServiceException;

    /**
     * Determines if the user can apply for this course.
     * The user can apply if he not logged in or has a {@code UserRole.STUDENT} role
     * and has not previously applied for this course.
     *
     * @param courseId course id for verification.
     * @param user     user for verification.
     * @return {@code true} if the user can apply for the specified course, {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    boolean isAvailableApplyButton(int courseId, User user) throws ServiceException;

    Map<Course, String> getApplyByStudent(int studentId, String local) throws ServiceException;

    /**
     * Returns all existing applications from the database, sorted by {@code ApplyComparator}.
     *
     * @param local language used by the user.
     * @return all existing applications from the database in sorted form.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<Apply> getAllApply(String local) throws ServiceException;

    /**
     * Returns all applications for which the course title or the student name
     * partially matches the entered.
     *
     * @param local language used by the user.
     * @return all applications for which the course title or the student name partially matches the entered.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<Apply> searchApply(String studentName, String courseTitle, String local) throws ServiceException;

    /**
     * Rejects student application at specific course.
     *
     * <p>In order to cancel the application it is necessary to write a review about
     * the student in the archive and delete the application</p>
     *
     * @param studentId student id for rejection.
     * @param courseId  course id for rejection.
     * @param review    review about student for rejection.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void rejectApply(int studentId, int courseId, String review) throws ServiceException;

    /**
     * Add review about student work.
     * If previously about this student on this course the review was left, then it will be updated.
     *
     * @param studentId student id for rejection.
     * @param courseId  course id for rejection.
     * @param review    review about student for rejection.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void leaveReview(int studentId, int courseId, String review) throws ServiceException;

    /**
     * Enrolls specific student in a specific course.
     * To enroll a user in a course, you need to change the status of his application to "Enrolled in course".
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void enrollInCourse(int studentId, int courseId) throws ServiceException;

    /**
     * Enrolls specific student in a learning on specific course.
     * To enroll a user in a learning, you need to change the status of his application to "Studying".
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void enrollInLearning(int studentId, int courseId) throws ServiceException;
}
