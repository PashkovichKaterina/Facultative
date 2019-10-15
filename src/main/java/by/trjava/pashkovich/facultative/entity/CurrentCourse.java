package by.trjava.pashkovich.facultative.entity;

public class CurrentCourse {
    private Course course;
    private String beginDate;
    private int mark;

    public String getBeginDate() {
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

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
