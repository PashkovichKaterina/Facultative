package by.trjava.pashkovich.facultative.controller.command.provider;

import by.trjava.pashkovich.facultative.controller.command.Command;
import by.trjava.pashkovich.facultative.controller.command.impl.*;
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
        commands.put(CommandVariety.ADD_WORK_FORM, new ShowAddWorkFormCommand());
        commands.put(CommandVariety.VIEW_SEARCH_COURSE_RESULT, new ViewSearchCourseResultCommand());
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
