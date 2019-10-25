package by.trjava.pashkovich.facultative.entity;

import by.trjava.pashkovich.facultative.util.CustomFormatForDate;

import java.io.Serializable;
import java.util.Date;

/**
 * Class represents {@code UserRole.STUDENT} from the web-applications.
 *
 * <p>This class extends class {@link Person}.</p>
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Person
 * @since JDK1.0
 */
public class Student extends Person implements Serializable {

    private static final long serialVersionUID = -5557639098753339104L;

    /**
     * Student date of birth.
     */
    private Date dateOfBirth;

    /**
     * Student address.
     */
    private String address;

    /**
     * Returns student date of birth.
     *
     * @return student date of birth.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Returns student address.
     *
     * @return student address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets student date of birth.
     *
     * @param dateOfBirth student date of birth.
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets student address.
     *
     * @param address student address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Compares this user to the specified object.
     * The result is {@code true} if and only if the argument is not {@code null}
     * and is a {@code Student} object has the same fields value with the specified object.
     *
     * @param obj the object to compare this {@code Student} against.
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
        Student student = (Student) obj;
        return super.equals(student)
                && (dateOfBirth == null ? dateOfBirth == student.dateOfBirth : dateOfBirth.equals(student.dateOfBirth))
                && (address == null ? address == student.address : address.equals(student.address));
    }

    /**
     * Returns a hash code for this student.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return super.hashCode() + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode())
                + ((address == null) ? 0 : address.hashCode());
    }

    /**
     * Returns the string representation of this object.
     * The {@code toString} method for class {@code Student}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character {@code @},
     * and all fields int the format name of the field with a capital letter,
     * the at-sign character {@code =} and field value.
     *
     * @return the string representation of this object.
     */
    @Override
    public String toString() {
        return super.toString() + "; Date_of_birth=" + CustomFormatForDate.getUseClientDateFormat(dateOfBirth)
                + "; Address=" + address;
    }
}