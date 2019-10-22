package by.trjava.pashkovich.facultative.entity;

import java.util.Date;

public class CurrentCourse {
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
}
