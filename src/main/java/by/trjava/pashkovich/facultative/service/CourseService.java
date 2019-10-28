package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.CurrentCourse;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface CourseService {
    /**
     * Returns the course with id equal to the specified.
     *
     * @param id    course id
     * @param local language used by the user.
     * @return the course with id equal to the specified.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Course getCourseById(int id, String local) throws ServiceException;

    /**
     * Returns all courses defined in the database.
     *
     * @param local language used by the user.
     * @return all courses defined in the database.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<Course> getAllCourse(String local) throws ServiceException;

    /**
     * Returns all course category defined in the database.
     *
     * @param local language used by the user.
     * @return all course category defined in the database.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<String> getAllCategory(String local) throws ServiceException;

    /**
     * Returns the requirements for the specified course in the form of skill-level.
     *
     * @param id    course id.
     * @param local language used by the user.
     * @return the requirements for the specified course in the form of skill-level.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<String, String> getCourseRequirement(int id, String local) throws ServiceException;

    /**
     * Returns the timetable for the specified course in the form of day of week-time.
     *
     * @param id    course id.
     * @param local language used by the user.
     * @return the timetable for the specified course in the form of day of week-time.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<String, Date> getCourseTimetable(int id, String local) throws ServiceException;

    /**
     * Returns a set of courses on the specified parameters.
     *
     * <p>The search takes place according to only one parameter: category title or course title.
     * If the course title is specified, then all courses that partially contain the transmitted string are returned.
     * Otherwise, all courses that match the selected category are returned.</p>
     *
     * @param courseTitle course title.
     * @param category    category title.
     * @param local       language used by the user.
     * @return a set of courses on the specified parameters.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<Course> searchCourse(String courseTitle, String category, String local) throws ServiceException;

    /**
     * Returns all courses in which the student is currently studying.
     *
     * @param studentId student id.
     * @param local     language used by the user.
     * @return all courses in which the student is currently studying.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Set<CurrentCourse> getStudentCurrentCourse(int studentId, String local) throws ServiceException;

    /**
     * Returns all courses that are defined in the database with their current status.
     *
     * @param local language used by the user.
     * @return all courses that are defined in the database with their current status.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<Course, String> getAllCourseWithStatus(String local) throws ServiceException;

    /**
     * Returns all courses that are defined in the database with their current status
     * which fixed for the specific teacher.
     *
     * @param teacherId teacher id.
     * @param local     language used by the user.
     * @return all courses that are defined in the database with their current status
     * which fixed for the specific teacher.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<Course, String> getCourseWithStatusByTeacher(int teacherId, String local) throws ServiceException;

    /**
     * Returns category id which title equals with specific.
     *
     * @param categoryTitle category title.
     * @param local         language used by the user.
     * @return category id which title equals with specific.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    int getCategoryIdByCategoryTitle(String categoryTitle, String local) throws ServiceException;

    /**
     * Insert course in database if possible.
     *
     * <p>The course can be inserted if there are no courses
     * with the same name, teacher and description are indicated.</p>
     *
     * @param request the {@code HttpServletRequest} object.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void insertCourse(HttpServletRequest request) throws ServiceException;

    /**
     * Checks whether it is currently possible to complete learning for this course.
     *
     * <p>It possible if the number of classes held is equal to the required number
     * and a review is written for each student</p>
     *
     * @param courseId course id.
     * @return {@code true} if there is available to end learning on this course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    boolean isEndCourseButtonAvailable(int courseId) throws ServiceException;

    /**
     * Graduates from the current course.
     * <p>To end learning on the course, it is necessary for each student who has been leaning
     * to write down the start and end dates and the average grade. Delete all grades, works,
     * classes and applications with status "Learning" for the specified course.</p>
     *
     * @param courseId course id.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void endCourse(int courseId) throws ServiceException;

    /**
     * Returns all course with status.
     *
     * @param courseTitle partial course title.
     * @return all course with status.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<Course, String> getCourseWithStatusByPartialMatchTitle(String courseTitle, String local) throws ServiceException;
}
