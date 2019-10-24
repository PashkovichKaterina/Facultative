package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Map;
import java.util.Set;

public interface ApplyService {
    void insertApply(int courseId, User user) throws ServiceException;

    boolean isAvailableApplyButton(int courseId, User user) throws ServiceException;

    Map<Course, String> getApplyByStudent(int studentId, String local) throws ServiceException;

    Set<Apply> getAllApply(String local) throws ServiceException;

    Set<Apply> searchApply(String studentName, String courseTitle, String local) throws ServiceException;

    void rejectApply(int studentId, int courseId, String review) throws ServiceException;

    void leaveReview(int studentId, int courseId, String review) throws ServiceException;

    void enrollInCourse(int studentId, int courseId) throws ServiceException;

    void enrollInLearning(int studentId, int courseId) throws ServiceException;
}
