package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;

import java.util.Set;

public interface ArchiveDAO {
    void insertArchive(int studentId, int courseId, int averageMark, String startDate, String endDate, String review) throws DAOException;

    boolean isContains(int studentId, int courseId) throws DAOException;

    Set<ArchiveCourse> getArchiveCourseByStudent(int studentId) throws DAOException;
}
