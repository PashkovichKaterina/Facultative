package by.trjava.pashkovich.facultative.entity.installation;

import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.CurrentCourse;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CurrentCourseInstaller {
    public static void install(CurrentCourse currentCourse, ResultSet resultSet)throws InstallerException  {
        if (!Optional.ofNullable(currentCourse).isPresent() || !Optional.ofNullable(resultSet).isPresent()) {
        throw new InstallerException("Empty data for install CurrentCourse");
    }
        try {
            Course course = new Course();
            CourseInstaller.install(course, resultSet);
            currentCourse.setCourse(course);
            currentCourse.setBeginDate(resultSet.getString(Variable.START_DATE));
            currentCourse.setMark(resultSet.getInt(Variable.MARK));
        } catch (SQLException e) {
            throw new InstallerException("Invalid sql result", e);
        }
    }
}
