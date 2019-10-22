package by.trjava.pashkovich.facultative.entity;

import java.io.Serializable;

public class Teacher extends Person implements Serializable {
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

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

    @Override
    public int hashCode() {
        return super.hashCode() + ((position == null) ? 0 : position.hashCode());
    }

    @Override
    public String toString() {
        return super.toString() + "; Position=" + position;
    }
}
