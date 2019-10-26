package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface CourseDAO {
    /**
     * Returns all course on Russian are stored in the database.
     *
     * @return all course on Russian are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Course> getAllCourseOnRu() throws DAOException;

    /**
     * Returns all course on English are stored in the database.
     *
     * @return all course on English are stored in the database.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Course> getAllCourseOnEn() throws DAOException;

    /**
     * Returns all course on Russian with specific category title.
     *
     * @param category category title.
     * @return all course on Russian with specific category title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Course> getCourseByCategoryOnRu(String category) throws DAOException;

    /**
     * Returns all course on English with specific category title.
     *
     * @param category category title.
     * @return all course on English with specific category title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Course> getCourseByCategoryOnEn(String category) throws DAOException;

    /**
     * Returns course on Russian with specific id.
     *
     * @param courseId course id.
     * @return course on Russian with specific id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Course getCourseByIdOnRu(int courseId) throws DAOException;

    /**
     * Returns course on English with specific id.
     *
     * @param courseId course id.
     * @return course on English with specific id.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Course getCourseByIdOnEn(int courseId) throws DAOException;

    /**
     * Returns all course on Russian with specific partial course title.
     *
     * @param courseTitle partial course title.
     * @return all course on Russian with specific partial course title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Course> getCourseByPartialMatchTitleOnRu(String courseTitle) throws DAOException;

    /**
     * Returns all course on English with specific partial course title.
     *
     * @param courseTitle partial course title.
     * @return all course on English with specific partial course title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Course> getCourseByPartialMatchTitleOnEn(String courseTitle) throws DAOException;

    /**
     * Returns all course on Russian with specific teacher.
     *
     * @param teacherId teacher id.
     * @return all course on Russian with specific teacher.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Course> getCourseByTeacherOnRu(int teacherId) throws DAOException;

    /**
     * Returns all course on English with specific teacher.
     *
     * @param teacherId teacher id.
     * @return all course on English with specific teacher.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<Course> getCourseByTeacherOnEn(int teacherId) throws DAOException;

    /**
     * Returns course id with specific title.
     *
     * @param title work title on Russian.
     * @return course id specific title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    int getCourseIdByRuTitle(String title) throws DAOException;

    /**
     * Returns course id with specific title.
     *
     * @param title work title on English.
     * @return course id specific title.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    int getCourseIdByEnTitle(String title) throws DAOException;

    /**
     * Returns course requirement on Russian in the format skill-level.
     *
     * @param courseId course id.
     * @return course requirement in the format skill-level.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<String, String> getCourseRequirementOnRu(int courseId) throws DAOException;

    /**
     * Returns course requirement on English in the format skill-level.
     *
     * @param courseId course id.
     * @return course requirement in the format skill-level.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<String, String> getCourseRequirementOnEn(int courseId) throws DAOException;

    /**
     * Returns course timetable on Russian in the format day-date.
     *
     * @param courseId course id.
     * @return course timetable on Russian in the format day-date.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<String, Date> getCourseTimetableOnRu(int courseId) throws DAOException;

    /**
     * Returns course timetable on English in the format day-date.
     *
     * @param courseId course id.
     * @return course timetable in the format day-date.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Map<String, Date> getCourseTimetableOnEn(int courseId) throws DAOException;

    /**
     * Returns the course on Russian at which the specified student is studying.
     *
     * @param studentId student id.
     * @return the course on Russian at which the specified student is studying.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<CurrentCourse> getStudentCurrentCourseOnRu(int studentId) throws DAOException;

    /**
     * Returns the course on English at which the specified student is studying.
     *
     * @param studentId student id.
     * @return the course on English at which the specified student is studying.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    Set<CurrentCourse> getStudentCurrentCourseOnEn(int studentId) throws DAOException;

    /**
     * Checks if the specific course is fixed by specific teacher.
     *
     * @param courseId  course id.
     * @param teacherId teacher id.l
     * @return {@code true} if the specific course is fixed by specific teacher, {@code false} otherwise.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    boolean isCourseFixedForTeacher(int courseId, int teacherId) throws DAOException;

    /**
     * Inserts a course with the specified parameters into the database.
     *
     * @param titleRu       course title on Russian.
     * @param titleEn       course title on English.
     * @param teacherId     teacher id.
     * @param classCount    course class count.
     * @param categoryId    category id.
     * @param availability  availability.
     * @param descriptionRu description on Russian.
     * @param descriptionEn description on English.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertCourse(String titleRu, String titleEn, int teacherId, int classCount, int categoryId, boolean availability,
                      String descriptionRu, String descriptionEn) throws DAOException;

    /**
     * Inserts a schedule in Russian at the specified course into the database.
     *
     * @param courseId course id.
     * @param day      class date.
     * @param time     class time.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertTimetableElementOnRu(int courseId, String day, String time) throws DAOException;

    /**
     * Inserts a schedule in English at the specified course into the database.
     *
     * @param courseId course id.
     * @param day      class date.
     * @param time     class time.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertTimetableElementOnEn(int courseId, String day, String time) throws DAOException;

    /**
     * Inserts a requirement in Russian at the specified course into the database.
     *
     * @param courseId course id.
     * @param skill    skill title on Russian.
     * @param level    level title on Russian.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertRequirementElementOnRu(int courseId, String skill, String level) throws DAOException;

    /**
     * Inserts a requirement in English at the specified course into the database.
     *
     * @param courseId course id.
     * @param skill    skill title on English.
     * @param level    level title on English.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    void insertRequirementElementOnEn(int courseId, String skill, String level) throws DAOException;
}
