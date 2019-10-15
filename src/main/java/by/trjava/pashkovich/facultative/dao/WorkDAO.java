package by.trjava.pashkovich.facultative.dao;


import by.trjava.pashkovich.facultative.dao.exception.DAOException;

import java.util.Set;

public interface WorkDAO {
    void insertWork(int courseId, String title) throws DAOException;

    void deleteWorkByCourse(int courseId) throws DAOException;

    Set<String> getWorkTitleByCourse(int courseId) throws DAOException;

    int getWorkId(int courseId, String title) throws DAOException;
}