package by.trjava.pashkovich.facultative.controller.command.variety;

public enum CommandVariety {
    //general
    CHANGE_LANGUAGE, LOGIN, LOGOUT, REGISTRATION, ACCOUNT,
    VIEW_ALL_COURSE, SEARCH_COURSE, VIEW_COURSE_INFO, VIEW_SEARCH_COURSE_RESULT,

    //student
    EDIT_STUDENT, EDIT_STUDENT_FORM, APPLY, CURRENT_COURSE,

    //teacher
    FIXED_COURSE, ADD_CLASS, ADD_CLASS_FORM, ADD_WORK, ADD_WORK_FORM
}