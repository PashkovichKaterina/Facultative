package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.MarkDAO;
import by.trjava.pashkovich.facultative.dao.UserDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.service.MarkService;
import by.trjava.pashkovich.facultative.service.comparator.PersonComparator;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.validation.CourseValidator;
import by.trjava.pashkovich.facultative.service.validation.MarkValidator;
import by.trjava.pashkovich.facultative.service.validation.WorkValidator;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MarkServiceImpl implements MarkService {
    /**
     * Returns the average mark of the specified student at the specified course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return the average mark of the specified student at the specified course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public int getStudentAverageMarkByCourse(int studentId, int courseId) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        if (!CourseValidator.checkCourseId(courseId)) {
            throw new ServiceException("Invalid data for get student average mark by course " + courseId);
        }
        try {
            return markDAO.getStudentAverageMarkByCourse(studentId, courseId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns the work title and the mark for the given work of the specified student at the specified course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return the work title and the mark for the given work of the specified student at the specified course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Map<String, Integer> getStudentMarkWithWorkTitleByCourse(int studentId, int courseId) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        if (!CourseValidator.checkCourseId(courseId)) {
            throw new ServiceException("Invalid data for get work title with student average mark by course " + courseId);
        }
        try {
            return markDAO.getStudentMarkWithWorkTitleByCourse(studentId, courseId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns a map of students with a list of marks for the specified course sorted by {@code PersonComparator<>}.
     *
     * @param courseId course id.
     * @return a map of students with a list of marks for the specified course in sorted order.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Map<Student, List<Integer>> getStudentWithMarkByCourse(int courseId) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        UserDAO userDAO = DAOFactory.getUserDAO();
        if (!CourseValidator.checkCourseId(courseId)) {
            throw new ServiceException("Invalid data for get student with list of marks by course " + courseId);
        }
        Map<Student, List<Integer>> studentWithMark = new TreeMap<>(new PersonComparator<>());
        try {
            for (Student student : userDAO.getAllStudentByCourse(courseId)) {
                studentWithMark.put(student, markDAO.getStudentMarkByCourse(student.getId(), courseId));
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return studentWithMark;
    }

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
    @Override
    public Map<Student, Integer> getStudentWithMarkByCourseWork(int courseId, String workTitle) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        if (!CourseValidator.checkCourseId(courseId)) {
            throw new ServiceException("Invalid data for get student with mark by course " + courseId + " and work title " + workTitle);
        }
        Map<Student, Integer> students = new TreeMap<>(new PersonComparator<>());
        try {
            for (Map.Entry<Student, Integer> student : markDAO.getStudentWithMarkByCourseWork(courseId, workTitle).entrySet()) {
                students.put(student.getKey(), student.getValue());
            }
            return students;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

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
    @Override
    public void updateMark(int studentId, int workId, int mark, int teacherId) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        if (!MarkValidator.checkMark(mark) || !WorkValidator.isWorkAvailableForTeacher(workId, teacherId)
                || !WorkValidator.isWorkAvailableForStudent(workId, studentId)) {
            throw new ServiceException("Invalid data for update mark by teacher with id = " + teacherId);
        }
        try {
            markDAO.updateMark(studentId, workId, mark);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
