package by.trjava.pashkovich.facultative.service.comparator;

import by.trjava.pashkovich.facultative.entity.ArchiveCourse;

import java.util.Comparator;

public class ArchiveComparatorByDate implements Comparator<ArchiveCourse> {
    @Override
    public int compare(ArchiveCourse o1, ArchiveCourse o2) {
        return o1.getBeginDate().compareTo(o2.getBeginDate());
    }
}
