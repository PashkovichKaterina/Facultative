package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.*;
import sun.java2d.loops.DrawGlyphListAA;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface CourseDAO {

    Set<Course> getAllCourseOnRu() throws DAOException;

    Set<Course> getAllCourseOnEn() throws DAOException;

    Set<Course> getCourseByCategoryOnRu(String category) throws DAOException;

    Set<Course> getCourseByCategoryOnEn(String category) throws DAOException;

    Course getCourseByIdOnRu(int courseId) throws DAOException;

    Course getCourseByIdOnEn(int courseId) throws DAOException;

    Set<Course> getCourseByPartialMatchTitleOnRu(String courseTitle) throws DAOException;

    Set<Course> getCourseByPartialMatchTitleOnEn(String courseTitle) throws DAOException;

    Set<Course> getCourseByTeacherOnRu(int teacherId) throws DAOException;

    Set<Course> getCourseByTeacherOnEn(int teacherId) throws DAOException;

    Set<String> getAllCategoryOnRu() throws DAOException;

    Set<String> getAllCategoryOnEn() throws DAOException;

    int getCourseIdByRuTitle(String title) throws DAOException;
    int getCourseIdByEnTitle(String title) throws DAOException;

    int getCategoryIdByCategoryTitleOnRu(String categoryTitle) throws DAOException;

    int getCategoryIdByCategoryTitleOnEn(String categoryTitle) throws DAOException;

    Map<String, String> getCourseRequirementOnRu(int courseId) throws DAOException;

    Map<String, String> getCourseRequirementOnEn(int courseId) throws DAOException;

    Map<String, Date> getCourseTimetableOnRu(int courseId) throws DAOException;

    Map<String, Date> getCourseTimetableOnEn(int courseId) throws DAOException;

    Set<CurrentCourse> getStudentCurrentCourseOnRu(int studentId) throws DAOException;

    Set<CurrentCourse> getStudentCurrentCourseOnEn(int studentId) throws DAOException;

    boolean isCourseFixedForTeacher(int courseId, int teacherId) throws DAOException;

    void insertCourse(String titleRu, String titleEn, int teacherId, int classCount, int categoryId, boolean availability,
                      String descriptionRu, String descriptionEn) throws DAOException;

    void insertTimetableElementOnRu(int courseId, String day, String time) throws DAOException;

    void insertTimetableElementOnEn(int courseId, String day, String time) throws DAOException;

    void insertRequirementElementOnRu(int courseId, String skill, String level) throws DAOException;

    void insertRequirementElementOnEn(int courseId, String skill, String level) throws DAOException;
}
