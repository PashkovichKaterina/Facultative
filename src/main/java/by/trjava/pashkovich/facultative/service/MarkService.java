package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface MarkService {
    /**
     * Returns the average mark of the specified student at the specified course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return the average mark of the specified student at the specified course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    int getStudentAverageMarkByCourse(int studentId, int courseId) throws ServiceException;

    /**
     * Returns the work title and the mark for the given work of the specified student at the specified course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return the work title and the mark for the given work of the specified student at the specified course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<String, Integer> getStudentMarkWithWorkTitleByCourse(int studentId, int courseId) throws ServiceException;

    /**
     * Returns a map of students with a list of marks for the specified course sorted by {@code PersonComparator<>}.
     *
     * @param courseId course if.
     * @return a map of students with a list of marks for the specified course in sorted order.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<Student, List<Integer>> getStudentWithMarkByCourse(int courseId) throws ServiceException;

    /**
     * Returns a map of students with average mark for the specified course
     * and specific work title sorted by {@code PersonComparator<>}.
     *
     * @param courseId  course id.
     * @param workTitle work title.
     * @return a map of students with average mark for the specified course
     * and specific work title in sorted order.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    Map<Student, Integer> getStudentWithMarkByCourseWork(int courseId, String workTitle) throws ServiceException;

    /**
     * Updates student mark for specified work if possible.
     *
     * <p>It is possible to update only if the work has been assigned a course that is fixed
     * to the specified teacher and the student is studying at this course.</p>
     *
     * @param studentId student id.
     * @param workId    work id.
     * @param mark      mark id.
     * @param teacherId teacher id.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    void updateMark(int studentId, int workId, int mark, int teacherId) throws ServiceException;
}
