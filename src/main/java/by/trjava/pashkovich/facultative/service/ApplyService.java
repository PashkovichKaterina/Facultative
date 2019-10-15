package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.exception.ServiceException;

import java.util.Set;

public interface ApplyService {
    void insertApply(int courseId, User user) throws ServiceException;

    boolean isAvailableApplyButton(int courseId, User user) throws ServiceException;

    Set<Apply> getApplyByStudent(Student student) throws ServiceException;
}
