package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.impl.*;

public class DAOFactory {
    private static final UserDAO userDAO = new DatabaseUserDaoImpl();
    private static final CourseDAO courseDAO = new DatabaseCourseDAOImpl();
    private static final ApplyDAO applyDAO = new DatabaseApplyDAOImpl();
    private static final ClassDAO classDAO = new DatabaseClassDAOImpl();
    private static final WorkDAO workDAO = new DatabaseWorkDAOImpl();
    private static final MarkDAO markDAO = new DatabaseMarkDAOImpl();
    private static final ArchiveDAO archiveDAO = new DatabaseArchiveDAOImpl();
    private static final SkillDAO skillDAO = new DatabaseSkillDAOImpl();
    private static final CategoryDAO categoryDAO = new DatabaseCategoryDAOImpl();

    private DAOFactory() {
    }

    public static UserDAO getUserDAO() {
        return userDAO;
    }

    public static CourseDAO getCourseDAO() {
        return courseDAO;
    }

    public static ApplyDAO getApplyDAO() {
        return applyDAO;
    }

    public static ClassDAO getClassDAO() {
        return classDAO;
    }

    public static WorkDAO getWorkDAO() {
        return workDAO;
    }

    public static MarkDAO getMarkDAO() {
        return markDAO;
    }

    public static ArchiveDAO getArchiveDAO() {
        return archiveDAO;
    }

    public static SkillDAO getSkillDAO() {
        return skillDAO;
    }

    public static CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }
}
