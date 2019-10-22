package by.trjava.pashkovich.facultative.dao;


import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Student;

import java.util.List;
import java.util.Map;

public interface MarkDAO {
    void insertMark(int studentId, int workId, int mark) throws DAOException;

    void updateMark(int studentId, int workId, int mark) throws DAOException;

    void deleteMarkByCourse(int courseId) throws DAOException;

    int getStudentAverageMarkByCourse(int studentId, int courseId) throws DAOException;

    List<Integer> getStudentMarkByCourse(int studentId, int courseId) throws DAOException;

    Map<String, Integer> getStudentMarkWithWorkTitleByCourse(int studentId, int courseId) throws DAOException;

    Map<Student, Integer> getStudentWithMarkByCourseWork(int courseId, String workTitle) throws DAOException;
}
