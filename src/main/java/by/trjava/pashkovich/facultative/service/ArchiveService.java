package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;

public interface ArchiveService {
    /**
     * Returns all courses that the specified student has passed along
     * like class {@code ArchiveCourse} with his average mark.
     *
     * @param studentId student id.
     * @param local     language used by the user.
     * @return all courses that the specified student has passed along with his average mark.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudent(int studentId, String local) throws ServiceException;

    /**
     * Returns review about specific student by specific course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return review about specific student by specific course.
     * @throws DAOException if an SQL syntax or Connection Pool error occurred.
     */
    String getReview(int studentId, int courseId) throws ServiceException;
}
