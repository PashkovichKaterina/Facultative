package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.characteristics.ApplyStatus;

import java.util.Set;

public interface ApplyDAO {
    void insertApply(int studentId, int courseId) throws DAOException;

    void updateApplyStatus(int studentId, int courseId, ApplyStatus applyStatus) throws DAOException;

    void deleteApply(int studentId, int courseId) throws DAOException;

    boolean isContains(int studentId, int courseId) throws DAOException;

    Set<Apply> getApplyByStudent(Student student) throws DAOException;
}
