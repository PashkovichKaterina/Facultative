package by.trjava.pashkovich.facultative.entity.installation;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CourseInstaller {
    public static void install(Course course, ResultSet resultSet) throws InstallerException {
        if (!Optional.ofNullable(course).isPresent() || !Optional.ofNullable(resultSet).isPresent()) {
            throw new InstallerException("Empty data for install Course");
        }
        try {
            course.setId(resultSet.getInt(Variable.COURSE_ID));
            course.setTitle(resultSet.getString(Variable.TITLE));
            course.setCategory(resultSet.getString(Variable.CATEGORY_TITLE));
            course.setTeacher(resultSet.getString(Variable.TEACHER));
            course.setClassesNumber(resultSet.getInt(Variable.NUMBER_OF_CLASSES));
            course.setDescription(resultSet.getString(Variable.DESCRIPTION));
            course.setAvailability(resultSet.getBoolean(Variable.AVAILABILITY));
        } catch (SQLException e) {
            throw new InstallerException("Invalid sql result", e);
        }
    }
}
