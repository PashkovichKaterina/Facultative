package by.trjava.pashkovich.facultative.entity;

public class Apply {
    private User user;
    private Course course;
    private String status;

    public User getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }

    public Course getCourse() {
        return course;
    }

    public void setUser(User user) {
        this.user = user;
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
        return (user == null ? user == request.user : user.equals(request.user))
                && (status == null ? status == request.status : status.equals(request.status))
                && (course == null ? course == request.course : course.equals(request.course));
    }

    @Override
    public int hashCode() {
        return ((user == null) ? 0 : user.hashCode()) + ((course == null) ? 0 : course.hashCode())
                + ((status == null) ? 0 : status.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@USER=" + user + " ;COURSE=" + course + " ;STATUS=" + status;
    }
}
