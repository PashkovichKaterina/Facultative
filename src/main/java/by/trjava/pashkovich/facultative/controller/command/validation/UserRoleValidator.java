package by.trjava.pashkovich.facultative.controller.command.validation;

import by.trjava.pashkovich.facultative.controller.command.exception.AuthorizationException;
import by.trjava.pashkovich.facultative.controller.command.exception.AuthenticationException;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;


public class UserRoleValidator {
    public static boolean isAdministratorLoggedIn(User user) throws AuthorizationException, AuthenticationException {
        if (user == null) {
            throw new AuthenticationException("User is not authenticated");
        }
        if (user.getRole() != UserRole.ADMINISTRATOR) {
            throw new AuthorizationException("User role is not " + UserRole.ADMINISTRATOR);
        }
        return true;
    }

    public static boolean isStudentLoggedIn(User user) throws AuthorizationException, AuthenticationException {
        if (user == null) {
            throw new AuthenticationException("User is not authenticated");
        }
        if (user.getRole() != UserRole.STUDENT) {
            throw new AuthorizationException("User role is not " + UserRole.STUDENT);
        }
        return true;
    }
}
