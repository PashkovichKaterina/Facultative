package by.trjava.pashkovich.facultative.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student extends Person {
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private Date dateOfBirth;
    private String address;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }


    public String getAddress() {
        return address;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    @Override
    public int hashCode() {
        return super.hashCode() + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode())
                + ((address == null) ? 0 : address.hashCode());
    }

    @Override
    public String toString() {
        return super.toString() + "; Date_of_birth=" + CustomFormatForDate.getUseClientDateFormat(dateOfBirth)
                + "; Address=" + address;
    }
}