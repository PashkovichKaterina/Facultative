package by.trjava.pashkovich.facultative.service.comparator;

import by.trjava.pashkovich.facultative.entity.ArchiveCourse;

import java.util.Comparator;

/**
 * A comparison function, which imposes a total ordering on some collection of {@code ArchiveCourse} object.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Comparator
 * @see ArchiveCourse
 * @since JDK 1.0
 */
public class ArchiveComparator implements Comparator<ArchiveCourse> {
    /**
     * Compares two objects first by course end date, then by course begin date in decreasing order
     * and course title in alphabetical order.
     *
     * @param o1 the first ArchiveCourse object to be compared.
     * @param o2 the second ArchiveCourse object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     */
    @Override
    public int compare(ArchiveCourse o1, ArchiveCourse o2) {
        if (o1 != null && o2 != null) {

            if (o1.getEndDate() != null && o2.getEndDate() != null
                    && !o1.getEndDate().equals(o2.getEndDate()))
                return o2.getEndDate().compareTo(o1.getEndDate());

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
