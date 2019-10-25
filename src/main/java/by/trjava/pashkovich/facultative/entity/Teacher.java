package by.trjava.pashkovich.facultative.entity;

import java.io.Serializable;

/**
 * Class represents {@code UserRole.TEACHER} from the web-applications.
 *
 * <p>This class extends class {@link Person}.</p>
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Person
 * @since JDK1.0
 */
public class Teacher extends Person implements Serializable {

    private static final long serialVersionUID = 6436874293471072469L;

    /**
     * Teacher job position.
     */
    private String position;

    /**
     * Returns teacher job position.
     *
     * @return teacher job position.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets teacher job position.
     *
     * @param position teacher job position.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Compares this user to the specified object.
     * The result is {@code true} if and only if the argument is not {@code null}
     * and is a {@code Teacher} object has the same fields value with the specified object.
     *
     * @param obj the object to compare this {@code Teacher} against.
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
        Teacher teacher = (Teacher) obj;
        return super.equals(teacher)
                && (position == null ? position == teacher.position : position.equals(teacher.position));
    }

    /**
     * Returns a hash code for this teacher.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return super.hashCode() + ((position == null) ? 0 : position.hashCode());
    }

    /**
     * Returns the string representation of this object.
     * The {@code toString} method for class {@code Teacher}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character {@code @},
     * and all fields int the format name of the field with a capital letter,
     * the at-sign character {@code =} and field value.
     *
     * @return the string representation of this object.
     */
    @Override
    public String toString() {
        return super.toString() + "; Position=" + position;
    }
}
