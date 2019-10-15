package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.ArchiveDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;
import by.trjava.pashkovich.facultative.service.ArchiveService;
import by.trjava.pashkovich.facultative.service.comparator.ArchiveComparatorByDate;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Set;
import java.util.TreeSet;

public class ArchiveServiceImpl implements ArchiveService {
    @Override
    public Set<ArchiveCourse> getArchiveCourseByStudent(int studentId) throws ServiceException {
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        try {
            Set<ArchiveCourse> courses = new TreeSet<>(new ArchiveComparatorByDate());
            for (ArchiveCourse archive : archiveDAO.getArchiveCourseByStudent(studentId)) {
                courses.add(archive);
            }
            return courses;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
