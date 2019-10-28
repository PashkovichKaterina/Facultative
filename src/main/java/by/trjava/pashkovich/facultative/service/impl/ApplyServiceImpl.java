package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.ApplyDAO;
import by.trjava.pashkovich.facultative.dao.ArchiveDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.comparator.ApplyComparator;
import by.trjava.pashkovich.facultative.service.exception.*;
import by.trjava.pashkovich.facultative.util.MessageManager;
import by.trjava.pashkovich.facultative.service.validation.CourseValidator;
import by.trjava.pashkovich.facultative.service.validation.FieldValidator;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ApplyServiceImpl implements ApplyService {
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
    @Override
    public void insertApply(int courseId, User user) throws ServiceException {
        if (FieldValidator.isEmpty(user)) {
            throw new NotLoginException("Only authorized users can submit an application");
        }
        if (user.getRole() != UserRole.STUDENT) {
            throw new UnavailableOperationException("Only students can apply");
        }
        if (!CourseValidator.checkCourseId(courseId)) {
            throw new NoSuchCourseException("Invalid course id: " + courseId);
        }
        Student student = (Student) user;
        if (FieldValidator.isEmpty(student.getSurname())
                && FieldValidator.isEmpty(student.getName())
                && FieldValidator.isEmpty(student.getPatronymic())
                && FieldValidator.isEmpty(student.getPhone())) {
            throw new RequiredAccountInformationNotEnteredException("To apply, fill in the required account data");
        } else {
            ApplyDAO applyDAO = DAOFactory.getApplyDAO();
            ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
            try {
                if (!applyDAO.isContains(courseId, student.getId()) && !archiveDAO.isContains(student.getId(), courseId)) {
                    applyDAO.insertApply(student.getId(), courseId, 1);
                }
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }
    }

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
    @Override
    public boolean isAvailableApplyButton(int courseId, User user) throws ServiceException {
        if (FieldValidator.isEmpty(user)) {
            return true;
        }
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        try {
            if (user.getClass() == Student.class && !applyDAO.isContains(user.getId(), courseId)
                    && !archiveDAO.isContains(user.getId(), courseId)) {
                return true;
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public Map<Course, String> getApplyByStudent(int studentId, String local) throws ServiceException {
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        try {
            return MessageManager.enLocal.equals(local) ? applyDAO.getApplyByStudentOnEn(studentId)
                    : applyDAO.getApplyByStudentOnRu(studentId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns all existing applications from the database, sorted by {@code ApplyComparator}.
     *
     * @param local language used by the user.
     * @return all existing applications from the database in sorted form.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Set<Apply> getAllApply(String local) throws ServiceException {
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        Set<Apply> applies = new TreeSet<>(new ApplyComparator());
        try {
            applies.addAll(MessageManager.enLocal.equals(local)
                    ? applyDAO.getAllApplyOnEn()
                    : applyDAO.getAllApplyOnRu());
            return applies;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns all applications for which the course title or the student name
     * partially matches the entered.
     *
     * @param local language used by the user.
     * @return all applications for which the course title or the student name partially matches the entered.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Set<Apply> searchApply(String studentName, String courseTitle, String local) throws ServiceException {
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        Set<Apply> applies = new TreeSet<>(new ApplyComparator());
        Set<Apply> ap;
        try {
            if (!FieldValidator.isEmpty(studentName)) {
                ap = MessageManager.enLocal.equals(local) ? applyDAO.getAllApplyByStudentNameOnEn(studentName)
                        : applyDAO.getAllApplyByStudentNameOnRu(studentName);
            } else if (!FieldValidator.isEmpty(courseTitle)) {
                ap = MessageManager.enLocal.equals(local) ? applyDAO.getAllApplyByCourseTitleOnEn(courseTitle)
                        : applyDAO.getAllApplyByCourseTitleOnRu(courseTitle);
            } else {
                ap = MessageManager.enLocal.equals(local) ? applyDAO.getAllApplyOnEn()
                        : applyDAO.getAllApplyOnRu();
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        applies.addAll(ap);
        return applies;

    }

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
    @Override
    public void rejectApply(int studentId, int courseId, String review) throws ServiceException {
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        try {
            if (!CourseValidator.checkCourseId(courseId) || !applyDAO.isContains(studentId, courseId)) {
                throw new ServiceException("Invalid data for reject apply");
            }
            if (archiveDAO.isContains(studentId, courseId)) {
                archiveDAO.updateArchive(studentId, courseId, 0, null, null);
            } else {
                archiveDAO.insertArchive(studentId, courseId, 0, null, null, review);
            }
            applyDAO.deleteApply(studentId, courseId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Add review about student work.
     * If previously about this student on this course the review was left, then it will be updated.
     *
     * @param studentId student id for rejection.
     * @param courseId  course id for rejection.
     * @param review    review about student for rejection.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public void leaveReview(int studentId, int courseId, String review) throws ServiceException {
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        try {
            if (CourseValidator.checkCourseId(courseId) && applyDAO.isContains(studentId, courseId)) {
                if (archiveDAO.isContains(studentId, courseId)) {
                    archiveDAO.updateReview(studentId, courseId, review);
                } else {
                    archiveDAO.insertArchive(studentId, courseId, 0, null, null, review);
                }
            } else {
                throw new InvalidDataTypeException("Invalid data for leave review about student " + studentId + " on course " + courseId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Enrolls specific student in a specific course.
     * To enroll a user in a course, you need to change the status of his application to "Enrolled in course".
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public void enrollInCourse(int studentId, int courseId) throws ServiceException {
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        try {
            if (CourseValidator.checkCourseId(courseId) && applyDAO.isContains(studentId, courseId)) {
                applyDAO.updateApplyStatus(studentId, courseId, 2);
            } else {
                throw new InvalidDataTypeException("Invalid data for enroll student" + studentId + " in course " + courseId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Enrolls specific student in a learning on specific course.
     * To enroll a user in a learning, you need to change the status of his application to "Studying".
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public void enrollInLearning(int studentId, int courseId) throws ServiceException {
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        try {
            if (CourseValidator.checkCourseId(courseId) && applyDAO.isContains(studentId, courseId)) {
                applyDAO.updateApplyStatus(studentId, courseId, 3);
            } else {
                throw new InvalidDataTypeException("Invalid data for enroll student" + studentId + " in learning by course " + courseId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}