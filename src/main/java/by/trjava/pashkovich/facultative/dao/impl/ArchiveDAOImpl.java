package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.ArchiveDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.ArchiveCourse;
import by.trjava.pashkovich.facultative.entity.Course;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;
import by.trjava.pashkovich.facultative.entity.installation.CourseInstaller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ArchiveDAOImpl implements ArchiveDAO {

    @Override
    public void insertArchive(int studentId, int courseId, int averageMark, String startDate, String endDate, String review)
            throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_ARCHIVE)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setInt(3, averageMark);
            statement.setString(4, startDate);
            statement.setString(5, endDate);
            statement.setString(6, review);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public boolean isContains(int studentId, int courseId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        boolean result;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_ARCHIVE)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                result = resultSet.next();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return result;
    }

    @Override
    public Set<ArchiveCourse> getArchiveCourseByStudent(int studentId) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        Set<ArchiveCourse> archives = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_ARCHIVE_COURSE_BY_STUDENT)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ArchiveCourse archiveCourse = new ArchiveCourse();
                    Course course = new Course();
                    CourseInstaller.install(course, resultSet);
                    archiveCourse.setCourse(course);
                    archiveCourse.setBeginDate(resultSet.getString(Variable.START_DATE));
                    archiveCourse.setEndDate(resultSet.getString(Variable.END_DATE));
                    archives.add(archiveCourse);
                }
            }
        } catch (SQLException | InstallerException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return archives;
    }
}
