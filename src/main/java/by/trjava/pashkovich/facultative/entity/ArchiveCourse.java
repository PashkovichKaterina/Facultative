package by.trjava.pashkovich.facultative.entity;

public class ArchiveCourse {
    private Course course;
    private String beginDate;
    private String endDate;

    public Course getCourse() {
        return course;
    }


    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }


    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

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

    @Override
    public int hashCode() {
        return ((course == null) ? 0 : course.hashCode())
                + ((beginDate == null) ? 0 : beginDate.hashCode()) + ((endDate == null) ? 0 : endDate.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@COURSE=" + course + " ;BEGIN_DATE=" + beginDate
                + " ;END_DATE=" + endDate;
    }
}
