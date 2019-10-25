package by.trjava.pashkovich.facultative.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Class represents basic information about course from archive for student.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Course
 * @since JDK1.0
 */
public class ArchiveCourse implements Serializable {

    private static final long serialVersionUID = -3245508961792584818L;

    /**
     * Course from archive.
     */
    private Course course;

    /**
     * Course start date.
     */
    private Date beginDate;

    /**
     * Course end date.
     */
    private Date endDate;

    /**
     * Returns course from archive.
     *
     * @return course from archive.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Returns course begin date.
     *
     * @return course begin date.
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Returns course end date.
     *
     * @return course end date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets course from archive.
     *
     * @param course course from archive.
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Sets course start date.
     *
     * @param beginDate course start date.
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Sets course end date.
     *
     * @param endDate course end date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Compares this user to the specified object.
     * The result is {@code true} if and only if the argument is not {@code null}
     * and is a {@code ArchiveCourse} object has the same fields value with the specified object.
     *
     * @param obj the object to compare this {@code ArchiveCourse} against.
     * @return {@code true} if the given object has the same fields value with the specified object,
     * {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ArchiveCourse archiveCourse = (ArchiveCourse) obj;
        return (course == null ? course == archiveCourse.course : course.equals(archiveCourse.course))
                && (beginDate == null ? beginDate == archiveCourse.beginDate : beginDate.equals(archiveCourse.beginDate))
                && (endDate == null ? endDate == archiveCourse.endDate : endDate.equals(archiveCourse.endDate));
    }

    /**
     * Returns a hash code for this archive course.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return ((course == null) ? 0 : course.hashCode())
                + ((beginDate == null) ? 0 : beginDate.hashCode()) + ((endDate == null) ? 0 : endDate.hashCode());
    }

    /**
     * Returns the string representation of this object.
     * The {@code toString} method for class {@code ArchiveCourse}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character {@code @},
     * and all fields int the format name of the field with a capital letter,
     * the at-sign character {@code =} and field value.
     *
     * @return the string representation of this object.
     */

    @Override
    public String toString() {
        return getClass().getName() + "@Course=" + course + "; Begin_date=" + beginDate
                + "; End_date=" + endDate;
    }
}
