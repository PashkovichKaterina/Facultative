package by.trjava.pashkovich.facultative.entity;

import java.io.Serializable;

/**
 * Class represents basic information about apply from the web-application.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Student
 * @see Course
 * @since JDK1.0
 */
public class Apply implements Serializable {

    private static final long serialVersionUID = -958224171551045925L;

    /**
     * Student who applied.
     */
    private Student student;

    /**
     * Course for which the student applied.
     */
    private Course course;

    /**
     * Application status.
     */
    private String status;

    /**
     * Returns student who applied.
     *
     * @return student who applied.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Returns application status.
     *
     * @return application status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns course for which the student applied.
     *
     * @return course for which the student applied.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets student who applied.
     *
     * @param student student who applied.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Sets application status.
     *
     * @param status application status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets course for which the student applied.
     *
     * @param course course for which the student applied.
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Compares this user to the specified object.
     * The result is {@code true} if and only if the argument is not {@code null}
     * and is a {@code Apply} object has the same fields value with the specified object.
     *
     * @param obj the object to compare this {@code Apply} against.
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
        Apply request = (Apply) obj;
        return (student == null ? student == request.student : student.equals(request.student))
                && (status == null ? status == request.status : status.equals(request.status))
                && (course == null ? course == request.course : course.equals(request.course));
    }

    /**
     * Returns a hash code for this apply.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return ((student == null) ? 0 : student.hashCode()) + ((course == null) ? 0 : course.hashCode())
                + ((status == null) ? 0 : status.hashCode());
    }

    /**
     * Returns the string representation of this object.
     * The {@code toString} method for class {@code Apply}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character {@code @},
     * and all fields int the format name of the field with a capital letter,
     * the at-sign character {@code =} and field value.
     *
     * @return the string representation of this object.
     */
    @Override
    public String toString() {
        return getClass().getName() + "@Student=" + student + "; Course=" + course + "; Status=" + status;
    }
}
