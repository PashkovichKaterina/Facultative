package by.trjava.pashkovich.facultative.entity;

import java.io.Serializable;

/**
 * Class represents basic information about course from web-application.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @since JDK1.0
 */
public class Course implements Serializable {

    private static final long serialVersionUID = -7799416096293259302L;

    /**
     * Course id integer number. Each course has a unique id number.
     */
    private int id;

    /**
     * Course title. Each course has a unique title.
     */
    private String title;

    /**
     * Course teacher.
     */
    private String teacher;

    /**
     * Number of classes per course.
     */
    private int classesNumber;

    /**
     * Course description.
     */
    private String description;

    /**
     * Course category.
     */
    private String category;

    /**
     * Course availability.
     * If {@code true}, students can apply.
     */
    private boolean availability;

    /**
     * Returns uniq course id integer number.
     *
     * @return course id integer number.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns uniq course title.
     *
     * @return course title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns course teacher.
     *
     * @return course teacher.
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * Returns number of classes per course.
     *
     * @return number of classes per course.
     */
    public int getClassesNumber() {
        return classesNumber;
    }

    /**
     * Returns course description.
     *
     * @return course description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns course category.
     *
     * @return course category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns course availability.
     *
     * @return course availability.
     */
    public boolean getAvailability() {
        return availability;
    }

    /**
     * Sets uniq course id integer number.
     *
     * @param id course id integer number.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets uniq course title.
     *
     * @param title course id integer number.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets course teacher.
     *
     * @param teacher course teacher.
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    /**
     * Sets number of classes per course.
     *
     * @param classesNumber number of classes per course.
     */
    public void setClassesNumber(int classesNumber) {
        this.classesNumber = classesNumber;
    }

    /**
     * Sets course description.
     *
     * @param description course description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets course category.
     *
     * @param category course category.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets course availability.
     *
     * @param availability course availability.
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    /**
     * Compares this user to the specified object.
     * The result is {@code true} if and only if the argument is not {@code null}
     * and is a {@code Course} object has the same fields value with the specified object.
     *
     * @param obj the object to compare this {@code Course} against.
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
        Course course = (Course) obj;
        return id == course.id && classesNumber == course.classesNumber && availability == course.availability
                && (title == null ? title == course.title : title.equals(course.title))
                && (teacher == null ? teacher == course.teacher : teacher.equals(course.teacher))
                && (description == null ? description == course.description : description.equals(course.description))
                && (category == null ? category == course.category : category.equals(course.category));
    }

    /**
     * Returns a hash code for this course.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return 31 * id + 31 * classesNumber + ((title == null) ? 0 : title.hashCode())
                + ((teacher == null) ? 0 : teacher.hashCode()) + ((description == null) ? 0 : description.hashCode()) +
                ((category == null) ? 0 : category.hashCode());
    }

    /**
     * Returns the string representation of this object.
     * The {@code toString} method for class {@code Course}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character {@code @},
     * and all fields int the format name of the field with a capital letter,
     * the at-sign character {@code =} and field value.
     *
     * @return the string representation of this object.
     */
    @Override
    public String toString() {
        return getClass().getName() + "@Id=" + id + "; Title=" + title + "; Teacher=" + teacher
                + "; Number_of_classes=" + classesNumber + "; Category=" + category + "; Availability=" + availability
                + "; Description=" + description;
    }
}
