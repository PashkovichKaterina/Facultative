package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;

import java.util.Map;

public interface ClassDAO {
    void insertClass(int courseId, String date, String time) throws DAOException;

    boolean isContains(int courseId, String date) throws DAOException;

    void deleteClassByCourse(int courseId) throws DAOException;

    int getClassesCountByCourse(int courseId) throws DAOException;

    Map<String, String> getClassDateTimeByCourse(int courseId) throws DAOException;
}
