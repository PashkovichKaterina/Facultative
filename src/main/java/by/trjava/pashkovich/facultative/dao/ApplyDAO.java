package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.characteristic.ApplyStatus;

import java.util.Map;
import java.util.Set;

public interface ApplyDAO {
    void insertApply(int studentId, int courseId) throws DAOException;

    void updateApplyStatus(int studentId, int courseId, int applyStatus) throws DAOException;

    void deleteApply(int studentId, int courseId) throws DAOException;

    void deleteAllLearnApplyByCourse(int courseId) throws DAOException;

    boolean isContains(int studentId, int courseId) throws DAOException;

    Map<Course, String> getApplyByStudentOnRu(int studentId) throws DAOException;

    Map<Course, String> getApplyByStudentOnEn(int studentId) throws DAOException;

    Set<Apply> getAllApplyOnRu() throws DAOException;

    Set<Apply> getAllApplyOnEn() throws DAOException;

    Set<Apply> getAllApplyByStudentNameOnRu(String studentName) throws DAOException;

    Set<Apply> getAllApplyByStudentNameOnEn(String studentName) throws DAOException;

    Set<Apply> getAllApplyByCourseTitleOnRu(String studentName) throws DAOException;

    Set<Apply> getAllApplyByCourseTitleOnEn(String studentName) throws DAOException;
}
