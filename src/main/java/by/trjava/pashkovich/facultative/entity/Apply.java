package by.trjava.pashkovich.facultative.entity;

import java.io.Serializable;

public class Apply implements Serializable {
    private Student student;
    private Course course;
    private String status;

    public Student getStudent() {
        return student;
    }

    public String getStatus() {
        return status;
    }

    public Course getCourse() {
        return course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

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

    @Override
    public int hashCode() {
        return ((student == null) ? 0 : student.hashCode()) + ((course == null) ? 0 : course.hashCode())
                + ((status == null) ? 0 : status.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@Student=" + student + "; Course=" + course + "; Status=" + status;
    }
}
