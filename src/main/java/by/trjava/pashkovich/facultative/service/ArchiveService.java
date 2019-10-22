package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.ArchiveCourse;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Set;

public interface ArchiveService {
    Set<ArchiveCourse> getArchiveCourseByStudent(int studentId, String local) throws ServiceException;
}
