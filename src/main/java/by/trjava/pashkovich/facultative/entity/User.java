package by.trjava.pashkovich.facultative.entity;

import by.trjava.pashkovich.facultative.entity.characteristics.UserRole;

public class User {
    private int id;
    private String login;
    private String email;
    private String password;
    private UserRole role;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

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

    @Override
    public int hashCode() {
        return 31 * id + ((role == null) ? 0 : role.hashCode()) + ((login == null) ? 0 : login.hashCode())
                + ((password == null) ? 0 : password.hashCode()) + ((email == null) ? 0 : email.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@Id=" + id + "; Login=" + login + "; Email=" + email + "; Password=" + password
                + "; Role=" + role;
    }
}
