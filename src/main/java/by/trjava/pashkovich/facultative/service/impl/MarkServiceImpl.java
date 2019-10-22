package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.CourseDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.MarkDAO;
import by.trjava.pashkovich.facultative.dao.UserDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.service.MarkService;
import by.trjava.pashkovich.facultative.service.comparator.StudentComparator;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;
import by.trjava.pashkovich.facultative.service.validation.MarkValidator;
import by.trjava.pashkovich.facultative.service.validation.WorkValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MarkServiceImpl implements MarkService {
    @Override
    public int getStudentAverageMarkByCourse(int studentId, int courseId) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        try {
            return markDAO.getStudentAverageMarkByCourse(studentId, courseId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Integer> getStudentMarkWithWorkTitleByCourse(int studentId, int courseId) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        try {
            return markDAO.getStudentMarkWithWorkTitleByCourse(studentId, courseId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Map<Student, List<Integer>> getStudentWithMarkByCourse(int courseId) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        UserDAO userDAO = DAOFactory.getUserDAO();
        Map<Student, List<Integer>> studentWithMark = new TreeMap<>(new StudentComparator());
        try {
            for (Student student : userDAO.getAllStudentByCourse(courseId)) {
                studentWithMark.put(student, markDAO.getStudentMarkByCourse(student.getId(), courseId));
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return studentWithMark;
    }

    @Override
    public Map<Student, Integer> getStudentWithMarkByCourseWork(int courseId, String workTitle) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        Map<Student, Integer> students = new TreeMap<>(new StudentComparator());
        try {
            for (Map.Entry<Student, Integer> student : markDAO.getStudentWithMarkByCourseWork(courseId, workTitle).entrySet()) {
                students.put(student.getKey(), student.getValue());
            }
            return students;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateMark(int studentId, int workId, int mark, int teacherId) throws ServiceException {
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        if (!MarkValidator.checkMark(mark) || !WorkValidator.isWorkAvailableForTeacher(workId, teacherId)
                || !WorkValidator.isWorkAvailableForStudent(workId, studentId)) {
            throw new ServiceException("Invalid data for update mark by teacher with id = " + teacherId);
        }
        try {
            markDAO.updateMark(studentId, workId, mark);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
