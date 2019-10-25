package by.trjava.pashkovich.facultative.service.comparator;

import by.trjava.pashkovich.facultative.entity.Person;

import java.util.Comparator;

/**
 * A comparison function, which imposes a total ordering on some collection
 * of {@code Person} object and all the heirs from {@code Person} class.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Comparator
 * @see Person
 * @since JDK 1.0
 */
public class PersonComparator<T extends Person> implements Comparator<T> {
    /**
     * Compares two objects first by surname, then by name and patronymic.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     */
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
