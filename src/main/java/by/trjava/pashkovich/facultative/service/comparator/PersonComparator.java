package by.trjava.pashkovich.facultative.service.comparator;

import by.trjava.pashkovich.facultative.entity.Person;

import java.util.Comparator;

public class PersonComparator<T extends Person> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        if (o1 != null && o2 != null) {
            if (o1.getSurname() != null && o2.getSurname() != null
                    && !o1.getSurname().equals(o2.getSurname()))
                return o1.getSurname().compareTo(o2.getSurname());

            if (o1.getName() != null && o2.getName() != null
                    && !o1.getName().equals(o2.getName()))
                return o1.getName().compareTo(o2.getName());

            if (o1.getPatronymic() != null && o2.getPatronymic() != null
                    && !o1.getPatronymic().equals(o2.getPatronymic()))
                return o1.getPatronymic().compareTo(o2.getPatronymic());
        }
        if (o1 == null && o2 != null)
            return 1;
        if (o1 != null)
            return -1;
        return 0;
    }
}
