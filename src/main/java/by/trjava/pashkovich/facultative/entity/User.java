package by.trjava.pashkovich.facultative.entity;

import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;

import java.io.Serializable;

/**
 * Class represents standard user from the web-application.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see UserRole
 * @since JDK1.0
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3699646580085329391L;

    /**
     * User id integer number. Each user has a unique id number.
     */
    private int id;

    /**
     * User login. Each user has a unique login.
     */
    private String login;

    /**
     * User email. Each user has a unique email.
     */
    private String email;

    /**
     * User password.
     */
    private String password;

    /**
     * User role in the system.
     * Each user can play only one role specified in {@link UserRole}.
     */
    private UserRole role;

    /**
     * Returns uniq user id integer number.
     *
     * @return user id integer number.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns uniq user login.
     *
     * @return user login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Returns uniq user email.
     *
     * @return user email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns user password.
     *
     * @return user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns user role.
     *
     * @return user role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets uniq user id integer number.
     *
     * @param id user id integer number.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets uniq user login.
     *
     * @param login user login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Sets uniq user email.
     *
     * @param email user email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets uniq user password.
     *
     * @param password user password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets uniq user role.
     *
     * @param role user role.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Compares this user to the specified object.
     * The result is {@code true} if and only if the argument is not {@code null}
     * and is a {@code User} object has the same fields value with the specified object.
     *
     * @param obj the object to compare this {@code User} against.
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
        User user = (User) obj;
        return id == user.id && role == user.role
                && (login == null ? login == user.login : login.equals(user.login))
                && (email == null ? email == user.email : email.equals(user.email))
                && (password == null ? password == user.password : password.equals(user.password));
    }

    /**
     * Returns a hash code for this user.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return 31 * id + ((role == null) ? 0 : role.hashCode()) + ((login == null) ? 0 : login.hashCode())
                + ((password == null) ? 0 : password.hashCode()) + ((email == null) ? 0 : email.hashCode());
    }

    /**
     * Returns the string representation of this object.
     * The {@code toString} method for class {@code User}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character {@code @},
     * and all fields int the format name of the field with a capital letter,
     * the at-sign character {@code =} and field value.
     *
     * @return the string representation of this object.
     */
    @Override
    public String toString() {
        return getClass().getName() + "@Id=" + id + "; Login=" + login + "; Email=" + email + "; Password=" + password
                + "; Role=" + role;
    }
}
