package by.trjava.pashkovich.facultative.service;

import by.trjava.pashkovich.facultative.service.impl.*;

public class ServiceFactory {
    private static final UserService userService = new UserServiceImpl();
    private static final CourseService courseService = new CourseServiceImpl();
    private static final ApplyService applyService = new ApplyServiceImpl();
    private static final ArchiveService archiveService = new ArchiveServiceImpl();
    private static final ClassService classService = new ClassServiceImpl();
    private static final MarkService markService = new MarkServiceImpl();
    private static final WorkService workService = new WorkServiceImpl();
    private static final SkillService skillService = new SkillServiceImpl();
    private static final CategoryService categoryService = new CategoryServiceImpl();

    private ServiceFactory() {
    }

    public static UserService getUserService() {
        return userService;
    }

    public static CourseService getCourseService() {
        return courseService;
    }

    public static ApplyService getApplyService() {
        return applyService;
    }

    public static ArchiveService getArchiveService() {
        return archiveService;
    }

    public static ClassService getClassService() {
        return classService;
    }

    public static MarkService getMarkService() {
        return markService;
    }

    public static WorkService getWorkService() {
        return workService;
    }

    public static SkillService getSkillService() {
        return skillService;
    }

    public static CategoryService getCategoryService() {
        return categoryService;
    }
}
