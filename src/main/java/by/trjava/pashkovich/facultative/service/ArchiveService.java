package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.ArchiveCourse;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;
import java.util.Set;

public interface ArchiveService {
    Map<ArchiveCourse,Integer> getArchiveCourseWithMarkByStudent(int studentId, String local) throws ServiceException;
}
