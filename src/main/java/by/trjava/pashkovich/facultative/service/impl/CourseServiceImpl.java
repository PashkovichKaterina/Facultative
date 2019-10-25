package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.*;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.impl.UserDaoImpl;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.CurrentCourse;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.comparator.CurrentCourseComparator;
import by.trjava.pashkovich.facultative.service.comparator.PersonComparator;
import by.trjava.pashkovich.facultative.service.exception.*;
import by.trjava.pashkovich.facultative.util.MessageManager;
import by.trjava.pashkovich.facultative.service.validation.CourseValidator;
import by.trjava.pashkovich.facultative.service.validation.FieldValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseServiceImpl implements CourseService {
    @Override
    public Course getCourseById(int id, String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        if (!CourseValidator.checkCourseId(id)) {
            throw new NoSuchCourseException("Invalid course id");
        }
        try {
            return MessageManager.enLocal.equals(local)
                    ? courseDAO.getCourseByIdOnEn(id)
                    : courseDAO.getCourseByIdOnRu(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    @Override
    public Set<Course> getAllCourse(String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? courseDAO.getAllCourseOnEn()
                    : courseDAO.getAllCourseOnRu();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Set<String> getAllCategory(String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            Set<String> categories = MessageManager.enLocal.equals(local)
                    ? courseDAO.getAllCategoryOnEn()
                    : courseDAO.getAllCategoryOnRu();
            return new TreeSet<>(categories);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, String> getCourseRequirement(int id, String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        if (!CourseValidator.checkCourseId(id)) {
            throw new NoSuchCourseException("Invalid course id");
        }
        try {
            return MessageManager.enLocal.equals(local)
                    ? courseDAO.getCourseRequirementOnEn(id)
                    : courseDAO.getCourseRequirementOnRu(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Date> getCourseTimetable(int id, String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        if (!CourseValidator.checkCourseId(id)) {
            throw new NoSuchCourseException("Invalid course id");
        }
        try {
            return MessageManager.enLocal.equals(local)
                    ? courseDAO.getCourseTimetableOnEn(id)
                    : courseDAO.getCourseTimetableOnRu(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Course> searchCourse(String courseTitle, String category, String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        Set<Course> courses;
        if (FieldValidator.isEmpty(courseTitle) && FieldValidator.isEmpty(category)) {
            return getAllCourse(local);
        }
        if (!FieldValidator.isEmpty(courseTitle)) {
            try {
                courses = MessageManager.enLocal.equals(local)
                        ? courseDAO.getCourseByPartialMatchTitleOnEn(courseTitle)
                        : courseDAO.getCourseByPartialMatchTitleOnRu(courseTitle);
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        } else {
            if (!CourseValidator.checkCategoryTitle(category, local)) {
                throw new NoSuchCategoryException("Invalid category");
            }
            try {
                courses = MessageManager.enLocal.equals(local)
                        ? courseDAO.getCourseByCategoryOnEn(category)
                        : courseDAO.getCourseByCategoryOnRu(category);
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }
        if (FieldValidator.isEmpty(courses)) {
            throw new NoSuchCourseException("Invalid course id");
        }
        return courses;
    }

    @Override
    public Set<CurrentCourse> getStudentCurrentCourse(int studentId, String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        Set<CurrentCourse> courses = new TreeSet<>(new CurrentCourseComparator());
        try {
            Set<CurrentCourse> currentCourses = MessageManager.enLocal.equals(local)
                    ? courseDAO.getStudentCurrentCourseOnEn(studentId)
                    : courseDAO.getStudentCurrentCourseOnRu(studentId);
            for (CurrentCourse c : currentCourses) {
                courses.add(c);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return courses;
    }

    @Override
    public Map<Course, String> getAllCourseWithStatus(String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        Map<Course, String> courseWithStatus = new HashMap<>();
        try {
            Set<Course> courses = MessageManager.enLocal.equals(local)
                    ? courseDAO.getAllCourseOnEn()
                    : courseDAO.getAllCourseOnRu();
            for (Course course : courses) {
                String status = getCourseStatus(course, local);
                courseWithStatus.put(course, status);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return courseWithStatus;
    }

    @Override
    public Map<Course, String> getCourseWithStatusByTeacher(int teacherId, String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        Map<Course, String> courseWithStatus = new HashMap<>();
        try {
            Set<Course> courses = MessageManager.enLocal.equals(local)
                    ? courseDAO.getCourseByTeacherOnEn(teacherId)
                    : courseDAO.getCourseByTeacherOnRu(teacherId);
            for (Course course : courses) {
                String status = getCourseStatus(course, local);
                courseWithStatus.put(course, status);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return courseWithStatus;
    }

    @Override
    public Set<Student> getAllStudentByCourse(int courseId) throws ServiceException {
        UserDAO userDAO = DAOFactory.getUserDAO();
        Set<Student> students = new TreeSet<>(new PersonComparator<>());
        try {
            for (Student student : userDAO.getAllStudentByCourse(courseId)) {
                students.add(student);
            }
            return students;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int getCategoryIdByCategoryTitle(String categoryTitle, String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? courseDAO.getCategoryIdByCategoryTitleOnEn(categoryTitle)
                    : courseDAO.getCategoryIdByCategoryTitleOnRu(categoryTitle);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void insertCourse(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        String local = (String) session.getAttribute(Variable.LOCAL);

        String titleRu = request.getParameter(Variable.TITLE_RU).trim();
        String titleEn = request.getParameter(Variable.TITLE_EN).trim();
        String category = request.getParameter(Variable.CATEGORY).trim();
        String descriptionRu = request.getParameter(Variable.DESCRIPTION_RU).trim();
        String descriptionEn = request.getParameter(Variable.DESCRIPTION_EN).trim();
        int teacherId = new Integer(request.getParameter(Variable.TEACHER));
        String count = request.getParameter(Variable.COUNT).trim();

        if (!CourseValidator.checkCategoryTitle(category, local)) {
            throw new ServiceException("Invalid data in selection fields");
        }
        if (CourseValidator.checkCourseTitleOnRu(titleRu, request)
                & CourseValidator.checkCourseTitleOnEn(titleEn, request)
                & CourseValidator.checkClassCount(count, request)
                & CourseValidator.checkDescriptionOnRu(descriptionRu, request)
                & CourseValidator.checkDescriptionOnEn(descriptionEn, request)) {
            try {
                int courseCount = new Integer(count);
                int categoryId = getCategoryIdByCategoryTitle(category, local);
                courseDAO.insertCourse(titleRu, titleEn, teacherId, courseCount, categoryId, true, descriptionRu, descriptionEn);
                int courseId = courseDAO.getCourseIdByRuTitle(titleRu);
                for (Map.Entry<String, String> s : requirementSkills(request).entrySet()) {
                    courseDAO.insertRequirementElementOnRu(courseId, s.getKey(), s.getValue());
                }
                for (Map.Entry<String, String> s : timetableElements(request).entrySet()) {
                    courseDAO.insertTimetableElementOnRu(courseId, s.getKey(), s.getValue());
                }
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        } else {
            request.setAttribute(Variable.TITLE_RU, titleRu);
            request.setAttribute(Variable.TITLE_EN, titleEn);
            request.setAttribute(Variable.COUNT, count);
            request.setAttribute(Variable.DESCRIPTION_RU, descriptionRu);
            request.setAttribute(Variable.DESCRIPTION_EN, descriptionEn);
            throw new AddCourseException("Invalid data to add course");
        }
    }

    @Override
    public boolean isEndCourseButtonAvailable(int courseId) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        ClassDAO classDAO = DAOFactory.getClassDAO();
        UserDAO userDAO = DAOFactory.getUserDAO();
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        try {
            int courseClassCount = courseDAO.getCourseByIdOnEn(courseId).getClassesNumber();
            int classCount = classDAO.getClassesCountByCourse(courseId);
            if (classCount < courseClassCount) {
                return false;
            }
            for (Student student : userDAO.getAllStudentByCourse(courseId)) {
                if (!archiveDAO.isContains(student.getId(), courseId)) {
                    return false;
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    private Map<String, String> requirementSkills(HttpServletRequest request) {
        String skill = "skill\\d+";
        String level = "level\\d+";
        return method(request, skill, level);
    }

    private Map<String, String> timetableElements(HttpServletRequest request) {
        String day = "day\\d+";
        String time = "time\\d+";
        return method(request, day, time);
    }

    private Map<String, String> method(HttpServletRequest request, String key, String value) {
        Map<String, String> result = new HashMap<>();
        Pattern keyPatter = Pattern.compile(key);
        Pattern valuePattern = Pattern.compile(value);

        String keyElement = null;
        Matcher m;
        Enumeration<String> attr = request.getParameterNames();
        while (attr.hasMoreElements()) {
            String elem = attr.nextElement();
            m = keyPatter.matcher(elem);
            if (m.matches()) {
                keyElement = request.getParameter(elem);
                result.put(keyElement, null);
            }

            m = valuePattern.matcher(elem);
            if (m.matches()) {
                result.put(keyElement, request.getParameter(elem));

            }
        }
        return result;
    }

    private String getCourseStatus(Course course, String local) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        MessageManager messageManager = MessageManager.getInstance();
        try {
            if (classDAO.getClassesCountByCourse(course.getId()) > 0) {
                return messageManager.getMessage(InformMessage.COURSE_IN_PROGRESS, local);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        if (course.getAvailability()) {
            return messageManager.getMessage(InformMessage.COURSE_NOT_STARTED, local);
        }
        return messageManager.getMessage(InformMessage.COURSE_UNAVAILABLE, local);
    }

    @Override
    public void endCourse(int courseId) throws ServiceException {
        ClassDAO classDAO = DAOFactory.getClassDAO();
        MarkDAO markDAO = DAOFactory.getMarkDAO();
        UserDAO userDAO = DAOFactory.getUserDAO();
        ArchiveDAO archiveDAO = DAOFactory.getArchiveDAO();
        WorkDAO workDAO = DAOFactory.getWorkDAO();
        ApplyDAO applyDAO = DAOFactory.getApplyDAO();
        try {
            for (Student student : userDAO.getAllStudentByCourse(courseId)) {
                Date beginDate = classDAO.getBeginDateByCourse(courseId);
                Date endDate = classDAO.getEndDateByCourse(courseId);
                int averageMark = markDAO.getStudentAverageMarkByCourse(student.getId(), courseId);
                archiveDAO.updateArchive(student.getId(), courseId, averageMark, beginDate, endDate);
            }
            markDAO.deleteMarkByCourse(courseId);
            workDAO.deleteWorkByCourse(courseId);
            classDAO.deleteClassByCourse(courseId);
            applyDAO.deleteAllLearnApplyByCourse(courseId);
        } catch (DAOException e) {
            System.out.println(e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
