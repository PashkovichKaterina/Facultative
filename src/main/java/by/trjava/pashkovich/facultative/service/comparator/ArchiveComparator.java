package by.trjava.pashkovich.facultative.service.comparator;

import by.trjava.pashkovich.facultative.entity.ArchiveCourse;

import java.util.Comparator;

public class ArchiveComparator implements Comparator<ArchiveCourse> {
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
