package by.trjava.pashkovich.facultative.service.impl;

import by.trjava.pashkovich.facultative.constants.InformMessage;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.*;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.CurrentCourse;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.service.CourseService;
import by.trjava.pashkovich.facultative.service.comparator.CurrentCourseComparator;
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
    /**
     * Returns the course with id equal to the specified.
     *
     * @param id    course id
     * @param local language used by the user.
     * @return the course with id equal to the specified.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * Returns all courses defined in the database.
     *
     * @param local language used by the user.
     * @return all courses defined in the database.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * Returns all course category defined in the database.
     *
     * @param local language used by the user.
     * @return all course category defined in the database.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Set<String> getAllCategory(String local) throws ServiceException {
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO();
        try {
            Set<String> categories = MessageManager.enLocal.equals(local)
                    ? categoryDAO.getAllCategoryOnEn()
                    : categoryDAO.getAllCategoryOnRu();
            return new TreeSet<>(categories);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns the requirements for the specified course in the form of skill-level.
     *
     * @param id    course id.
     * @param local language used by the user.
     * @return the requirements for the specified course in the form of skill-level.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * Returns the timetable for the specified course in the form of day of week-time.
     *
     * @param id    course id.
     * @param local language used by the user.
     * @return the timetable for the specified course in the form of day of week-time.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * Returns a set of courses on the specified parameters.
     *
     * <p>The search takes place according to only one parameter: category title or course title.
     * If the course title is specified, then all courses that partially contain the transmitted string are returned.
     * Otherwise, all courses that match the selected category are returned.</p>
     *
     * @param courseTitle course title.
     * @param category    category title.
     * @param local       language used by the user.
     * @return a set of courses on the specified parameters.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * Returns all courses in which the student is currently studying.
     *
     * @param studentId student id.
     * @param local     language used by the user.
     * @return all courses in which the student is currently studying.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Set<CurrentCourse> getStudentCurrentCourse(int studentId, String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        Set<CurrentCourse> courses = new TreeSet<>(new CurrentCourseComparator());
        try {
            Set<CurrentCourse> currentCourses = MessageManager.enLocal.equals(local)
                    ? courseDAO.getStudentCurrentCourseOnEn(studentId)
                    : courseDAO.getStudentCurrentCourseOnRu(studentId);
            courses.addAll(currentCourses);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return courses;
    }

    /**
     * Returns all courses that are defined in the database with their current status.
     *
     * @param local language used by the user.
     * @return all courses that are defined in the database with their current status.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * Returns all courses that are defined in the database with their current status
     * which fixed for the specific teacher.
     *
     * @param teacherId teacher id.
     * @param local     language used by the user.
     * @return all courses that are defined in the database with their current status
     * which fixed for the specific teacher.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * Returns category id which title equals with specific.
     *
     * @param categoryTitle category title.
     * @param local         language used by the user.
     * @return category id which title equals with specific.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public int getCategoryIdByCategoryTitle(String categoryTitle, String local) throws ServiceException {
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO();
        try {
            return MessageManager.enLocal.equals(local)
                    ? categoryDAO.getCategoryIdByCategoryTitleOnEn(categoryTitle)
                    : categoryDAO.getCategoryIdByCategoryTitleOnRu(categoryTitle);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Insert course in database if possible.
     *
     * <p>The course can be inserted if there are no courses
     * with the same name, teacher and description are indicated.</p>
     *
     * @param request the {@code HttpServletRequest} object.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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
                if (MessageManager.enLocal.equals(local)) {
                    for (Map.Entry<String, String> s : requirementSkills(request).entrySet()) {
                        System.out.println(s.getKey() + " - " + s.getValue());
                        courseDAO.insertRequirementElementOnEn(courseId, s.getKey(), s.getValue());
                    }
                    for (Map.Entry<String, String> s : timetableElements(request).entrySet()) {
                        courseDAO.insertTimetableElementOnEn(courseId, s.getKey(), s.getValue());
                    }
                } else {
                    for (Map.Entry<String, String> s : requirementSkills(request).entrySet()) {
                        courseDAO.insertRequirementElementOnRu(courseId, s.getKey(), s.getValue());
                    }
                    for (Map.Entry<String, String> s : timetableElements(request).entrySet()) {
                        courseDAO.insertTimetableElementOnRu(courseId, s.getKey(), s.getValue());
                    }
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

    /**
     * Checks whether it is currently possible to complete learning for this course.
     *
     * <p>It possible if the number of classes held is equal to the required number
     * and a review is written for each student</p>
     *
     * @param courseId course id.
     * @return {@code true} if there is available to end learning on this course.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * Returns map of the requirements which contains in the format skill*-level*
     * where * is a whole number.
     *
     * @param request the {@code HttpServletRequest} object.
     * @return map of the requirements like skill-level.
     */
    private Map<String, String> requirementSkills(HttpServletRequest request) {
        String skill = "skill\\d+";
        String level = "level\\d+";
        return selectParameterFromRequest(request, skill, level);
    }

    /**
     * Returns map of the requirements which contains in the format day*-time*
     * where * is a whole number.
     *
     * @param request the {@code HttpServletRequest} object.
     * @return map of the requirements like day-time.
     */
    private Map<String, String> timetableElements(HttpServletRequest request) {
        String day = "day\\d+";
        String time = "time\\d+";
        return selectParameterFromRequest(request, day, time);
    }

    private Map<String, String> selectParameterFromRequest(HttpServletRequest request, String key, String value) {
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

    /**
     * Return the course status.
     *
     * @param course {@code Course} object.
     * @param local  language used by the user.
     * @return the course status.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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

    /**
     * Graduates from the current course.
     *
     * <p>To end learning on the course, it is necessary for each student who has been leaning
     * to write down the start and end dates and the average grade. Delete all grades, works,
     * classes and applications with status "Learning" for the specified course.</p>
     *
     * @param courseId course id.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
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
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns all course with status.
     *
     * @param courseTitle partial course title.
     * @return all course with status.
     * @throws ServiceException if an exception occurred in the DAO layer.
     */
    @Override
    public Map<Course, String> getCourseWithStatusByPartialMatchTitle(String courseTitle, String local) throws ServiceException {
        CourseDAO courseDAO = DAOFactory.getCourseDAO();
        Map<Course, String> courseWithStatus = new HashMap<>();
        try {
            Set<Course> courses = MessageManager.enLocal.equals(local)
                    ? courseDAO.getCourseByPartialMatchTitleOnEn(courseTitle)
                    : courseDAO.getCourseByPartialMatchTitleOnRu(courseTitle);
            for (Course course : courses) {
                String status = getCourseStatus(course, local);
                courseWithStatus.put(course, status);
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return courseWithStatus;
    }
}
