package by.trjava.pashkovich.facultative.service.comparator;

import by.trjava.pashkovich.facultative.entity.CurrentCourse;

import java.util.Comparator;

/**
 * A comparison function, which imposes a total ordering on some collection of {@code CurrentCourse} object.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Comparator
 * @see CurrentCourse
 * @since JDK 1.0
 */
public class CurrentCourseComparator implements Comparator<CurrentCourse> {
    /**
     * Compares two objects first by course begin date in decreasing order
     * then by course title in alphabetical order.
     *
     * @param o1 the first CurrentCourse object to be compared.
     * @param o2 the second CurrentCourse object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     */
    @Override
    public int compare(CurrentCourse o1, CurrentCourse o2) {
        if (o1 != null && o2 != null) {
            if (o1.getBeginDate() != null && o2.getBeginDate() != null
                    && !o1.getBeginDate().equals(o2.getBeginDate()))
                return o2.getBeginDate().compareTo(o1.getBeginDate());

            if (o1.getCourse() != null && o1.getCourse().getTitle() != null
                    && o2.getCourse() != null && o2.getCourse().getTitle() != null
                    && !o2.getCourse().getTitle().equals(o1.getCourse().getTitle()))
                return o1.getCourse().getTitle().compareTo(o2.getCourse().getTitle());
        }
        if (o1 == null && o2 != null)
            return 1;
        if (o1 != null)
            return -1;
        return 0;
    }
}
