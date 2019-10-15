package by.trjava.pashkovich.facultative.entity.installation;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.entity.Person;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.Teacher;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristics.UserRole;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserInstaller {
    public static void install(User user, ResultSet resultSet) throws InstallerException {
        if (!Optional.ofNullable(user).isPresent() || !Optional.ofNullable(resultSet).isPresent()) {
            throw new InstallerException("Empty data for install User");
        }
        try {
            user.setId(resultSet.getInt(Variable.USER_ID));
            user.setLogin(resultSet.getString(Variable.LOGIN));
            user.setEmail(resultSet.getString(Variable.EMAIL));
            user.setPassword(resultSet.getString(Variable.PASSWORD));
            if (user instanceof Person) {
                ((Person) user).setSurname(resultSet.getString(Variable.SURNAME));
                ((Person) user).setName(resultSet.getString(Variable.NAME));
                ((Person) user).setPatronymic(resultSet.getString(Variable.PATRONYMIC));
                ((Person) user).setPhone(resultSet.getString(Variable.PHONE));
            }
            if (user instanceof Student) {
                ((Student) user).setAddress(resultSet.getString(Variable.ADDRESS));
                ((Student) user).setDateOfBirth(resultSet.getString(Variable.DATE_OF_BIRTH));
                user.setRole(UserRole.STUDENT);
            }
            if (user instanceof Teacher) {
                ((Teacher) user).setPosition(resultSet.getString(Variable.POSITION));
                user.setRole(UserRole.TEACHER);
            }
        } catch (SQLException e) {
            throw new InstallerException("Invalid sql result", e);
        }
    }
}