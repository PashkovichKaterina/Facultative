package by.trjava.pashkovich.facultative.controller.command.provider;

import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.impl.*;
import by.trjava.pashkovich.facultative.controller.command.impl.admin.*;
import by.trjava.pashkovich.facultative.controller.command.impl.student.CurrentCourseCommand;
import by.trjava.pashkovich.facultative.controller.command.impl.student.EditStudentCommand;
import by.trjava.pashkovich.facultative.controller.command.impl.student.ApplyCommand;
import by.trjava.pashkovich.facultative.controller.command.impl.student.ShowEditStudentFormCommand;
import by.trjava.pashkovich.facultative.controller.command.impl.teacher.*;
import by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final static CommandProvider instance = new CommandProvider();

    private Map<CommandVariety, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandVariety.ACCOUNT, new AccountCommand());
        commands.put(CommandVariety.LOGIN, new LoginCommand());
        commands.put(CommandVariety.LOGOUT, new LogoutCommand());
        commands.put(CommandVariety.REGISTRATION, new RegistrationCommand());
        commands.put(CommandVariety.EDIT_STUDENT, new EditStudentCommand());
        commands.put(CommandVariety.VIEW_ALL_COURSE, new ViewAllCourseCommand());
        commands.put(CommandVariety.SEARCH_COURSE, new SearchCourseCommand());
        commands.put(CommandVariety.VIEW_COURSE_INFO, new ViewCourseInfoCommand());
        commands.put(CommandVariety.APPLY, new ApplyCommand());
        commands.put(CommandVariety.CURRENT_COURSE, new CurrentCourseCommand());
        commands.put(CommandVariety.FIXED_COURSE, new FixedCourseCommand());
        commands.put(CommandVariety.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(CommandVariety.EDIT_STUDENT_FORM, new ShowEditStudentFormCommand());
        commands.put(CommandVariety.ADD_CLASS_FORM, new ShowAddClassFormCommand());
        commands.put(CommandVariety.ADD_CLASS, new AddClassCommand());
        commands.put(CommandVariety.ADD_WORK_FORM, new ShowAddWorkFormCommand());
        commands.put(CommandVariety.ADD_WORK, new AddWorkCommand());
        commands.put(CommandVariety.VIEW_SEARCH_COURSE_RESULT, new ViewSearchCourseResultCommand());
        commands.put(CommandVariety.SHOW_EDIT_MARK_FORM, new ShowEditMarkFormCommand());
        commands.put(CommandVariety.EDIT_STUDENT_MARK, new EditStudentMarkCommand());
        commands.put(CommandVariety.SHOW_ALL_SKILL, new ShowAllSkillCommand());
        commands.put(CommandVariety.SHOW_ALL_CATEGORY, new ShowAllCategoryCommand());
        commands.put(CommandVariety.SHOW_ALL_COURSE_FOR_ADMIN, new ShowAllCourseForAdmin());
        commands.put(CommandVariety.SHOW_ALL_STUDENT, new ShowAllStudentCommand());
        commands.put(CommandVariety.SEARCH_STUDENT, new SearchStudentCommand());
        commands.put(CommandVariety.SHOW_ALL_TEACHER, new ShowAllTeacherCommand());
        commands.put(CommandVariety.SEARCH_TEACHER, new SearchTeacherCommand());
        commands.put(CommandVariety.SHOW_ADD_COURSE_FORM, new ShowAddCourseFormCommand());
        commands.put(CommandVariety.ADD_COURSE, new AddCourseCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandVariety commandType;
        Command command = null;
        try {
            commandType = CommandVariety.valueOf(commandName);
            command = commands.get(commandType);
        } catch (IllegalArgumentException e) {

        }
        return command;
    }
}
