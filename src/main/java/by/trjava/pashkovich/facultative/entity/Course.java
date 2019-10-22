package by.trjava.pashkovich.facultative.entity;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String title;
    private String teacher;
    private int classesNumber;
    private String description;
    private String category;
    private boolean availability;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getClassesNumber() {
        return classesNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setClassesNumber(int classesNumber) {
        this.classesNumber = classesNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return id == course.id && classesNumber == course.classesNumber && availability == course.availability
                && (title == null ? title == course.title : title.equals(course.title))
                && (teacher == null ? teacher == course.teacher : teacher.equals(course.teacher))
                && (description == null ? description == course.description : description.equals(course.description))
                && (category == null ? category == course.category : category.equals(course.category));
    }

    @Override
    public int hashCode() {
        return 31 * id + 31 * classesNumber + Boolean.hashCode(availability) + ((title == null) ? 0 : title.hashCode())
                + ((teacher == null) ? 0 : teacher.hashCode()) + ((description == null) ? 0 : description.hashCode()) +
                ((category == null) ? 0 : category.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@Id=" + id + "; Title=" + title + "; Teacher=" + teacher
                + "; Number_of_classes=" + classesNumber + "; Category=" + category + "; Availability=" + availability
                + "; Description=" + description;
    }
}
