package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;

public interface ApplyService {
    void insertApply(int courseId, User user) throws ServiceException;

    boolean isAvailableApplyButton(int courseId, User user) throws ServiceException;

    Map<Course, String> getApplyByStudent(int studentId, String local) throws ServiceException;

}
