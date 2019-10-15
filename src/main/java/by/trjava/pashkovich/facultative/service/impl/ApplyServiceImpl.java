package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.dao.ApplyDAO;
import by.trjava.pashkovich.facultative.dao.ArchiveDAO;
import by.trjava.pashkovich.facultative.dao.DAOFactory;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Apply;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.ApplyService;
import by.trjava.pashkovich.facultative.service.exception.*;
import by.trjava.pashkovich.facultative.service.validation.CourseValidator;
import by.trjava.pashkovich.facultative.service.validation.FieldValidator;

import java.util.Set;

public class ApplyServiceImpl implements ApplyService {
    @Override
    public void insertApply(int courseId, User user) throws ServiceException {
        if (user == null) {
            throw new NotLoginException("Only authorized users can submit an application");
        }
        if (user.getClass() != Student.class) {
            throw new UnavailableOperationException("Only students can apply");
        }
        if (!CourseValidator.checkCourseId(courseId)) {
            throw new NoSuchCourseException();
        }
        Student student = (Student) user;
        if (FieldValidator.isEmpty(student.getSurname())
                && FieldValidator.isEmpty(student.getName())
                && FieldValidator.isEmpty(student.getPatronymic())
                && FieldValidator.isEmpty(student.getPhone())) {
            ApplyDAO applyDAO = DAOFactory.getApplyDAO();
            ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
            try {
                if (!applyDAO.isContains(courseId, student.getId()) && !archiveDAO.isContains(student.getId(), courseId)) {
                    applyDAO.insertApply(student.getId(), courseId);
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new RequiredAccountInformationNotEnteredException("To apply, fill in the required account data");
        }
    }

    @Override
    public boolean isAvailableApplyButton(int courseId, User user) throws ServiceException {
        if (user == null) {
            return true;
        }
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        try {
            if (user.getClass() == Student.class && !applyDAO.isContains(user.getId(), courseId)
                    && !archiveDAO.isContains(user.getId(), courseId)) {
                return true;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public Set<Apply> getApplyByStudent(Student student) throws ServiceException {
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        try {
            return applyDAO.getApplyByStudent(student);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
