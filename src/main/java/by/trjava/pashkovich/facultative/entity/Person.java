package by.trjava.pashkovich.facultative.entity;

import java.io.Serializable;

/**
 * Class represents basic field for person from web-application.
 *
 * <p>This class extends class {@link User}.</p>
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see User
 * @since JDK1.0
 */
public class Person extends User implements Serializable {

    private static final long serialVersionUID = -1251471135784414527L;

    /**
     * Person surname.
     * Can contain only letters of the Russian or English alphabet.
     */
    private String surname;

    /**
     * Person name.
     * Can contain only letters of the Russian or English alphabet.
     */
    private String name;

    /**
     * Person patronymic.
     * Can contain only letters of the Russian or English alphabet.
     */
    private String patronymic;

    /**
     * Person surname.
     * Can contain string in the format +372(**)***-**-**, where * is a numeral.
     */
    private String phone;

    /**
     * Returns person surname.
     *
     * @return user surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns person name.
     *
     * @return user name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns person patronymic.
     *
     * @return user patronymic.
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Returns person phone.
     *
     * @return user phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets person surname.
     *
     * @param surname person surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Sets person name.
     *
     * @param name person name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets person patronymic.
     *
     * @param patronymic person patronymic.
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Sets person phone.
     *
     * @param phone person phone.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Compares this user to the specified object.
     * The result is {@code true} if and only if the argument is not {@code null}
     * and is a {@code Person} object has the same fields value with the specified object.
     *
     * @param obj the object to compare this {@code Person} against.
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
        Person people = (Person) obj;
        return super.equals(people) && (surname == null ? surname == people.surname : surname.equals(people.surname))
                && (name == null ? name == people.name : name.equals(people.name))
                && (patronymic == null ? patronymic == people.patronymic : patronymic.equals(people.patronymic))
                && (phone == null ? phone == people.phone : phone.equals(people.phone));
    }

    /**
     * Returns a hash code for this person.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return super.hashCode() + ((surname == null) ? 0 : surname.hashCode()) + ((name == null) ? 0 : name.hashCode())
                + ((patronymic == null) ? 0 : patronymic.hashCode()) + ((phone == null) ? 0 : phone.hashCode());
    }

    /**
     * Returns the string representation of this object.
     * The {@code toString} method for class {@code Person}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character {@code @},
     * and all fields int the format name of the field with a capital letter,
     * the at-sign character {@code =} and field value.
     *
     * @return the string representation of this object.
     */
    @Override
    public String toString() {
        return super.toString() + "; Surname=" + surname + "; Name=" + name + "; Patronymic=" + patronymic
                + "; Phone=" + phone;
    }
}
