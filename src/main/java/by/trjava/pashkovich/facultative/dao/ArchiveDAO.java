package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;

import java.util.Date;
import java.util.Set;

public interface ArchiveDAO {
    void insertArchive(int studentId, int courseId, int averageMark, Date startDate, Date endDate, String review) throws DAOException;

    boolean isContains(int studentId, int courseId) throws DAOException;

    Set<ArchiveCourse> getArchiveCourseByStudentOnRu(int studentId) throws DAOException;

    Set<ArchiveCourse> getArchiveCourseByStudentOnEn(int studentId) throws DAOException;
}
