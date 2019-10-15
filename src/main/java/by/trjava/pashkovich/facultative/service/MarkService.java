package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;

public interface MarkService {
    int getStudentAverageMarkByCourse(int studentId, int courseId) throws ServiceException;

    Map<String, Integer> getStudentMarkWithWorkTitleByCourse(int studentId, int courseId) throws ServiceException;
}
