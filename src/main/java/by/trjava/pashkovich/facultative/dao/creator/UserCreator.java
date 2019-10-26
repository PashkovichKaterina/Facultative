package by.trjava.pashkovich.facultative.dao.creator;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.entity.Person;
import by.trjava.pashkovich.facultative.entity.Student;
import by.trjava.pashkovich.facultative.entity.Teacher;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;
import by.trjava.pashkovich.facultative.dao.exception.CreatorException;
import by.trjava.pashkovich.facultative.entity.factory.UserFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserCreator {
    public static User create(ResultSet resultSet) throws CreatorException {
        if (!Optional.ofNullable(resultSet).isPresent()) {
            throw new CreatorException("Empty data for install User");
        }
        User user;
        try {
            user = UserFactory.createUser(UserRole.getRole(resultSet.getInt(Variable.ROLE_ID)));
            user.setId(resultSet.getInt(Variable.USER_ID));
            user.setLogin(resultSet.getString(Variable.LOGIN));
            user.setEmail(resultSet.getString(Variable.EMAIL));
            user.setPassword(resultSet.getString(Variable.PASSWORD));
            user.setRole(UserRole.ADMINISTRATOR);
            if (user instanceof Person) {
                ((Person) user).setSurname(resultSet.getString(Variable.SURNAME));
                ((Person) user).setName(resultSet.getString(Variable.NAME));
                ((Person) user).setPatronymic(resultSet.getString(Variable.PATRONYMIC));
                ((Person) user).setPhone(resultSet.getString(Variable.PHONE));
            }
            if (user instanceof Student) {
                ((Student) user).setAddress(resultSet.getString(Variable.ADDRESS));
                ((Student) user).setDateOfBirth(resultSet.getDate(Variable.DATE_OF_BIRTH));
                user.setRole(UserRole.STUDENT);
            } else if (user instanceof Teacher) {
                ((Teacher) user).setPosition(resultSet.getString(Variable.POSITION));
                user.setRole(UserRole.TEACHER);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new CreatorException("Invalid sql result: " + e.getMessage(), e);
        }
        return user;
    }
}