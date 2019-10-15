package by.trjava.pashkovich.facultative.dao;


import by.trjava.pashkovich.facultative.dao.exception.DAOException;

import java.util.Map;

public interface MarkDAO {
    void insertMark(int studentId, int workId, int mark) throws DAOException;

    void updateMark(int studentId, int workId, int mark) throws DAOException;

    void deleteMarkByCourse(int courseId) throws DAOException;

    int getStudentAverageMarkByCourse(int studentId, int courseId) throws DAOException;

    Map<String, Integer> getStudentMarkWithWorkTitleByCourse(int studentId, int courseId) throws DAOException;
}
