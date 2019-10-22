package by.trjava.pashkovich.facultative.entity.factory;

import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.Teacher;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;

public class UserFactory {
    private UserFactory() {
    }

    public static User createUser(UserRole userRole) {
        User user = null;
        switch (userRole) {
            case STUDENT:
                user = new Student();
                break;
            case TEACHER:
                user = new Teacher();
                break;
            case ADMINISTRATOR:
                user = new User();
                break;
        }
        return user;
    }
}
