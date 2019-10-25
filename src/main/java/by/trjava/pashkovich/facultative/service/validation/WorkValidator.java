package by.trjava.pashkovich.facultative.service.validation;

import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.WorkDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

/**
 * Class containing methods for checking values used for check work parameters.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @since JDK 1.0
 */
public class WorkValidator {
    /**
     * Checks if the specified course contains work with the given title.
     *
     * @param workTitle work title for verification.
     * @param courseId  course id for verification.
     * @return {@code true} if the specific course does not contain work with the same title,
     * {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    public static boolean isWorkTitleContained(String workTitle, int courseId) throws ServiceException {
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        try {
            return workDAO.getWorkId(courseId, workTitle) > 0;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Checks if the specified teacher has access to work with the specified id.
     *
     * <p>The teacher has access to work if the work was assigned
     * at a course that is fixed to this teacher</p>
     *
     * @param workId    work is for verification
     * @param teacherId teacher id for verification.
     * @return {@code true} if the teacher has access to work with the specified id,
     * {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    public static boolean isWorkAvailableForTeacher(int workId, int teacherId) throws ServiceException {
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        try {
            return workDAO.isWorkAvailableForTeacher(workId, teacherId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Checks if the specified student has access to work with the specified id.
     *
     * <p>Student has access to work if the work was assigned
     * at a course at which the student is studying</p>
     *
     * @param workId    work is for verification
     * @param studentId student id for verification.
     * @return {@code true} if the student has access to work with the specified id,
     * {@code false} otherwise.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    public static boolean isWorkAvailableForStudent(int workId, int studentId) throws ServiceException {
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        try {
            return workDAO.isWorkAvailableForStudent(workId, studentId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
