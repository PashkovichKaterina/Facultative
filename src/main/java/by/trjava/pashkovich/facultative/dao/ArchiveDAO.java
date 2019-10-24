package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface ArchiveDAO {
    void insertArchive(int studentId, int courseId, int averageMark, Date startDate, Date endDate, String review) throws DAOException;

    void updateArchive(int studentId, int courseId, int averageMark, Date startDate, Date endDate) throws DAOException;

    void updateReview(int studentId, int courseId, String review) throws DAOException;

    boolean isContains(int studentId, int courseId) throws DAOException;

    String getReview(int studentId, int courseId) throws DAOException;

    Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudentOnRu(int studentId) throws DAOException;

    Map<ArchiveCourse, Integer> getArchiveCourseWithMarkByStudentOnEn(int studentId) throws DAOException;
}
