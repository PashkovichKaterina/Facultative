package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface ClassDAO {
    void insertClass(int courseId, Date date, String time) throws DAOException;

    void insertClass(int courseId, String date, String time) throws DAOException;

    boolean isContains(int courseId, Date date) throws DAOException;

    boolean isContains(int courseId, String date) throws DAOException;

    void deleteClassByCourse(int courseId) throws DAOException;

    int getClassesCountByCourse(int courseId) throws DAOException;

    Set<Date> getClassDateTimeByCourse(int courseId) throws DAOException;

    Set<String> getAllDaysOnRu() throws DAOException;

    Set<String> getAllDaysOnEn() throws DAOException;
}
