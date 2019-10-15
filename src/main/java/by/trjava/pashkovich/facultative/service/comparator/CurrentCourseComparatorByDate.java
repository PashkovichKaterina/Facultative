package by.trjava.pashkovich.facultative.service.comparator;

import by.trjava.pashkovich.facultative.entity.CurrentCourse;

import java.util.Comparator;

public class CurrentCourseComparatorByDate implements Comparator<CurrentCourse> {
    @Override
    public int compare(CurrentCourse o1, CurrentCourse o2) {
        return o1.getBeginDate().compareTo(o2.getBeginDate());
    }
}
