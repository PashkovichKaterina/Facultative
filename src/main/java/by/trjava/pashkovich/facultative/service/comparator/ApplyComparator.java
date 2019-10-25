package by.trjava.pashkovich.facultative.service.comparator;

import by.trjava.pashkovich.facultative.entity.Apply;

import java.util.Comparator;

/**
 * A comparison function, which imposes a total ordering on some collection of {@code Apply} object.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Comparator
 * @see Apply
 * @since JDK1.0
 */
public class ApplyComparator implements Comparator<Apply> {
    /**
     * Compares two objects first by surname, then by name and patronymic in alphabetical order.
     *
     * @param o1 the first Apply object to be compared.
     * @param o2 the second Apply object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     */
    @Override
    public int compare(Apply o1, Apply o2) {
        if (o1 != null && o2 != null) {
            if (o1.getStudent() != null && o2.getStudent() != null
                    && o1.getStudent().getSurname() != null && o2.getStudent().getSurname() != null
                    && !o1.getStudent().getSurname().equals(o2.getStudent().getSurname()))
                return o1.getStudent().getSurname().compareTo(o2.getStudent().getSurname());

            if (o1.getStudent() != null && o2.getStudent() != null
                    && o1.getStudent().getName() != null && o2.getStudent().getName() != null
                    && !o1.getStudent().getName().equals(o2.getStudent().getName()))
                return o1.getStudent().getName().compareTo(o2.getStudent().getName());

            if (o1.getStudent() != null && o2.getStudent() != null
                    && o1.getStudent().getPatronymic() != null && o2.getStudent().getPatronymic() != null
                    && !o1.getStudent().getPatronymic().equals(o2.getStudent().getPatronymic()))
                return o1.getStudent().getPatronymic().compareTo(o2.getStudent().getPatronymic());
        }
        if (o1 == null && o2 != null)
            return 1;
        if (o1 != null)
            return -1;
        return 0;
    }
}