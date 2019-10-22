package by.trjava.pashkovich.facultative.entity;

import java.io.Serializable;
import java.util.Date;

public class CurrentCourse implements Serializable {
    private Course course;
    private Date beginDate;
    private int mark;

    public Date getBeginDate() {
        return beginDate;
    }

    public Course getCourse() {
        return course;
    }

    public int getMark() {
        return mark;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

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

    @Override
    public int hashCode() {
        return 31 * mark + ((course == null) ? 0 : course.hashCode())
                + ((beginDate == null) ? 0 : beginDate.hashCode());
    }

    @Override
    public String toString() {
        return super.toString() + "; Course=" + course + "; Begin_date=" + beginDate + "; Mark=" + mark;
    }
}
