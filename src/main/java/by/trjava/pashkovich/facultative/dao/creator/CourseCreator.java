package by.trjava.pashkovich.facultative.dao.creator;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.dao.exception.CreatorException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CourseCreator {
    public static Course create(ResultSet resultSet) throws CreatorException {
        if (!Optional.ofNullable(resultSet).isPresent()) {
            throw new CreatorException("Empty data for install Course");
        }
        Course course = new Course();
        try {
            course.setId(resultSet.getInt(Variable.COURSE_ID));
            course.setTitle(resultSet.getString(Variable.TITLE));
            course.setCategory(resultSet.getString(Variable.CATEGORY_TITLE));
            course.setTeacher(resultSet.getString(Variable.TEACHER));
            course.setClassesNumber(resultSet.getInt(Variable.NUMBER_OF_CLASSES));
            course.setDescription(resultSet.getString(Variable.DESCRIPTION));
            course.setAvailability(resultSet.getBoolean(Variable.AVAILABILITY));
        } catch (SQLException e) {
            throw new CreatorException("Invalid sql result", e);
        }
        return course;
    }
}
