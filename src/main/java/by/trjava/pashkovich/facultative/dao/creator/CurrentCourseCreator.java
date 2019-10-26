package by.trjava.pashkovich.facultative.dao.creator;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.CurrentCourse;
import by.trjava.pashkovich.facultative.dao.exception.CreatorException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CurrentCourseCreator {
    public static CurrentCourse create(ResultSet resultSet) throws CreatorException {
        if (!Optional.ofNullable(resultSet).isPresent()) {
            throw new CreatorException("Empty data for install CurrentCourse");
        }
        CurrentCourse currentCourse = new CurrentCourse();
        try {
            Course course = CourseCreator.create(resultSet);
            currentCourse.setCourse(course);
            currentCourse.setBeginDate(resultSet.getDate(Variable.START_DATE));
            currentCourse.setMark(resultSet.getInt(Variable.MARK));
        } catch (SQLException e) {
            throw new CreatorException("Invalid sql result", e);
        }
        return currentCourse;
    }
}
