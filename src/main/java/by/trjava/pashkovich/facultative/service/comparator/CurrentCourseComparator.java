package by.trjava.pashkovich.facultative.service.comparator;

import by.trjava.pashkovich.facultative.entity.CurrentCourse;

import java.util.Comparator;

public class CurrentCourseComparator implements Comparator<CurrentCourse> {
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
