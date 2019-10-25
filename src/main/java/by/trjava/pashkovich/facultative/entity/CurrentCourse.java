package by.trjava.pashkovich.facultative.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Class represents basic information about current course for student.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Course
 * @since JDK1.0
 */
public class CurrentCourse implements Serializable {

    private static final long serialVersionUID = -116496369239799362L;

    /**
     * Current course.
     */
    private Course course;

    /**
     * Course start date.
     */
    private Date beginDate;

    /**
     * Average mark for current course.
     */
    private int mark;

    /**
     * Returns course start date.
     *
     * @return course start date.
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Returns current course.
     *
     * @return current course.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Returns course average mark.
     *
     * @return course average mark.
     */
    public int getMark() {
        return mark;
    }

    /**
     * Sets current course.
     *
     * @param course current course.
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
     * Sets course average mark.
     *
     * @param mark course average mark.
     */
    public void setMark(int mark) {
        this.mark = mark;
    }

    /**
     * Compares this user to the specified object.
     * The result is {@code true} if and only if the argument is not {@code null}
     * and is a {@code CurrentCourse} object has the same fields value with the specified object.
     *
     * @param obj the object to compare this {@code CurrentCourse} against.
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
        CurrentCourse currentCourse = (CurrentCourse) obj;
        return mark == currentCourse.mark
                && (course == null ? course == currentCourse.course : course.equals(currentCourse.course))
                && (beginDate == null ? beginDate == currentCourse.beginDate : beginDate.equals(currentCourse.beginDate));
    }

    /**
     * Returns a hash code for this current course.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return 31 * mark + ((course == null) ? 0 : course.hashCode())
                + ((beginDate == null) ? 0 : beginDate.hashCode());
    }

    /**
     * Returns the string representation of this object.
     * The {@code toString} method for class {@code CurrentCourse}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character {@code @},
     * and all fields int the format name of the field with a capital letter,
     * the at-sign character {@code =} and field value.
     *
     * @return the string representation of this object.
     */
    @Override
    public String toString() {
        return super.toString() + "; Course=" + course + "; Begin_date=" + beginDate + "; Mark=" + mark;
    }
}
