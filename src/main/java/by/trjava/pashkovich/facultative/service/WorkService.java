package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Set;

public interface WorkService {
    Set<String> getWorkTitleByCourse(int courseId) throws ServiceException;

    void addWork(String workTitle, int courseId, int teacherId) throws ServiceException;

    int getWorkId(int courseId, String title) throws ServiceException;
}
