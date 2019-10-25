package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.*;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.service.WorkService;
import by.trjava.pashkovich.facultative.service.exception.EmptyDataException;
import by.trjava.pashkovich.facultative.service.exception.InvalidDataTypeException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.exception.WorkTitleIsAlreadyContainedException;
import by.trjava.pashkovich.facultative.service.validation.CourseValidator;
import by.trjava.pashkovich.facultative.service.validation.FieldValidator;
import by.trjava.pashkovich.facultative.service.validation.WorkValidator;

import java.util.Set;

public class WorkServiceImpl implements WorkService {
    /**
     * Returns a set of all works title at the specified course.
     *
     * @param courseId course id.
     * @return a set of all works title at the specified course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Set<String> getWorkTitleByCourse(int courseId) throws ServiceException {
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        if (!CourseValidator.checkCourseId(courseId)) {
            throw new ServiceException("Invalid data for get work title");
        }
        try {
            return workDAO.getWorkTitleByCourse(courseId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Adds work with the work title at the specified course if possible.
     *
     * <p>You can add work if the specified course is fixed for the teacher and work with such a title
     * for this course has not been previously added.</p>
     *
     * @param workTitle work title.
     * @param courseId  course id.
     * @param teacherId teacher id.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public void addWork(String workTitle, int courseId, int teacherId) throws ServiceException {
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        UserDAO userDAO = DAOFactory.getUserDAO();
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        if (!CourseValidator.checkCourseId(courseId) || !CourseValidator.isCourseFixedForTeacher(courseId, teacherId)) {
            throw new ServiceException("Invalid data to add work to course = " + courseId + " by teacher = " + teacherId);
        }
        if (FieldValidator.isEmpty(workTitle)) {
            throw new EmptyDataException("Empty data to add work");
        }
        if (!FieldValidator.checkFormatForTextField(workTitle)) {
            throw new InvalidDataTypeException("Invalid format for work title");
        }
        if (WorkValidator.isWorkTitleContained(workTitle, courseId)) {
            throw new WorkTitleIsAlreadyContainedException("Course = " + courseId + " is contained work with title " + workTitle);
        }
        try {
            workDAO.insertWork(courseId, workTitle);
            int workId = workDAO.getWorkId(courseId, workTitle);
            for (Student student : userDAO.getAllStudentByCourse(courseId)) {
                markDAO.insertMark(student.getId(), workId, 0);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns the work id at the specified course and work title.
     *
     * @param courseId course id.
     * @param title    work title.
     * @return the id of the work at the specified course and work title.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public int getWorkId(int courseId, String title) throws ServiceException {
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        if (!CourseValidator.checkCourseId(courseId) || FieldValidator.isEmpty(title)) {
            throw new ServiceException("Invalid data for get work id");
        }
        try {
            return workDAO.getWorkId(courseId, title);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
