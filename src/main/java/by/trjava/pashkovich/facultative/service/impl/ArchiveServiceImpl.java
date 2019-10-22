package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.ArchiveDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;
import by.trjava.pashkovich.facultative.service.ArchiveService;
import by.trjava.pashkovich.facultative.service.comparator.ArchiveComparator;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.util.MessageManager;

import java.util.Set;
import java.util.TreeSet;

public class ArchiveServiceImpl implements ArchiveService {
    @Override
    public Set<ArchiveCourse> getArchiveCourseByStudent(int studentId, String local) throws ServiceException {
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        Set<ArchiveCourse> courses = new TreeSet<>(new ArchiveComparator());
        try {
            Set<ArchiveCourse> archiveCourses = MessageManager.enLocal.equals(local)
                    ? archiveDAO.getArchiveCourseByStudentOnEn(studentId)
                    : archiveDAO.getArchiveCourseByStudentOnRu(studentId);
            for (ArchiveCourse archive : archiveCourses) {
                courses.add(archive);
            }
            return courses;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
