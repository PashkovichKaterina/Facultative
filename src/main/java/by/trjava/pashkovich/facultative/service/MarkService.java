package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface MarkService {
    int getStudentAverageMarkByCourse(int studentId, int courseId) throws ServiceException;

    Map<String, Integer> getStudentMarkWithWorkTitleByCourse(int studentId, int courseId) throws ServiceException;

    Map<Student, List<Integer>> getStudentWithMarkByCourse(int courseId) throws ServiceException;

    Map<Student, Integer> getStudentWithMarkByCourseWork(int courseId, String workTitle) throws ServiceException;

    void updateMark(int studentId, int workId, int mark, int teacherId) throws ServiceException;
}
