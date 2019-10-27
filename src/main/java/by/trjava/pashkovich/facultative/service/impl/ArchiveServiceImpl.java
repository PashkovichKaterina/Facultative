package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.ArchiveDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.service.ArchiveService;
import by.trjava.pashkovich.facultative.service.comparator.ArchiveComparator;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ArchiveServiceImpl implements ArchiveService {
    /**
     * Returns all courses that the specified student has passed along
     * like class {@code ArchiveCourse} with his average mark.
     *
     * @param studentId student id.
     * @param local     language used by the user.
     * @return all courses that the specified student has passed along with his average mark.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudent(int studentId, String local) throws ServiceException {
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        Map<ArchiveCourse, Integer> courses = new TreeMap<>(new ArchiveComparator());
        try {
            Map<ArchiveCourse, Integer> archiveCourses = MessageManager.enLocal.equals(local)
                    ? archiveDAO.getArchiveCourseWithMarkByStudentOnEn(studentId)
                    : archiveDAO.getArchiveCourseWithMarkByStudentOnRu(studentId);
            for (Map.Entry<ArchiveCourse, Integer> archive : archiveCourses.entrySet()) {
                courses.put(archive.getKey(), archive.getValue());
            }
            return courses;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String getReview(int studentId, int courseId) throws ServiceException {
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        try {
            return archiveDAO.getReview(studentId, courseId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Map<Course, String> getAllCourseWithReviewByStudent(int studentId, String local) throws ServiceException {
        Map<Course, String> review = new HashMap<>();
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        try {
            Map<ArchiveCourse, Integer> archive = MessageManager.enLocal.equals(local)
                    ? archiveDAO.getArchiveCourseWithMarkByStudentOnEn(studentId)
                    : archiveDAO.getArchiveCourseWithMarkByStudentOnRu(studentId);
            for (Map.Entry<ArchiveCourse, Integer> i : archive.entrySet()) {
                review.put(i.getKey().getCourse(), archiveDAO.getReview(studentId, i.getKey().getCourse().getId()));
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return review;
    }
}
