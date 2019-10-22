package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.impl.*;

public class DAOFactory {
    private static final UserDAO userDAO = new UserDaoImpl();
    private static final CourseDAO courseDAO = new CourseDAOImpl();
    private static final ApplyDAO applyDAO = new ApplyDAOImpl();
    private static final ClassDAO classDAO = new ClassDAOImpl();
    private static final WorkDAO workDAO = new WorkDAOImpl();
    private static final MarkDAO markDAO = new MarkDAOImpl();
    private static final ArchiveDAO archiveDAO = new ArchiveDAOImpl();
    private static final SkillDAO skillDAO = new SkillDAOImpl();
    private static final CategoryDAO categoryDAO = new CategoryDAOImpl();

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
