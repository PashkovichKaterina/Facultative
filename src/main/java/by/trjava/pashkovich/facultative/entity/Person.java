package by.trjava.pashkovich.facultative.entity;

public class Person extends User {
    private String surname;
    private String name;
    private String patronymic;
    private String phone;

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    @Override
    public int hashCode() {
        return super.hashCode() + ((surname == null) ? 0 : surname.hashCode()) + ((name == null) ? 0 : name.hashCode())
                + ((patronymic == null) ? 0 : patronymic.hashCode()) + ((phone == null) ? 0 : phone.hashCode());
    }

    @Override
    public String toString() {
        return super.toString() + "; Surname=" + surname + "; Name=" + name + "; Patronymic=" + patronymic
                + "; Phone=" + phone;
    }
}
