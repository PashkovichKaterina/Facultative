package by.trjava.pashkovich.facultative.entity;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ArchiveCourseTest {
    @Test
    public void testEqualsReflexivity() {
        ArchiveCourse archiveCourse = new ArchiveCourse();
        archiveCourse.setCourse(new Course());
        archiveCourse.setBeginDate(new Date());
        archiveCourse.setEndDate(new Date());
        assertTrue(archiveCourse.equals(archiveCourse));
    }

    @Test
    public void testEqualsSymmetry() {
        ArchiveCourse archiveCourse1 = new ArchiveCourse();
        archiveCourse1.setCourse(new Course());
        archiveCourse1.setBeginDate(new Date());
        archiveCourse1.setEndDate(new Date());

        ArchiveCourse archiveCourse2 = new ArchiveCourse();
        archiveCourse2.setCourse(new Course());
        archiveCourse2.setBeginDate(new Date());
        archiveCourse2.setEndDate(new Date());

        assertEquals(archiveCourse1.equals(archiveCourse2), archiveCourse2.equals(archiveCourse1));
    }

    @Test
    public void testEqualsTransitivity() {
        ArchiveCourse archiveCourse1 = new ArchiveCourse();
        archiveCourse1.setCourse(new Course());
        archiveCourse1.setBeginDate(new Date());
        archiveCourse1.setEndDate(new Date());

        ArchiveCourse archiveCourse2 = new ArchiveCourse();
        archiveCourse2.setCourse(new Course());
        archiveCourse2.setBeginDate(new Date());
        archiveCourse2.setEndDate(new Date());

        ArchiveCourse archiveCourse3 = new ArchiveCourse();
        archiveCourse3.setCourse(new Course());
        archiveCourse3.setBeginDate(new Date());
        archiveCourse3.setEndDate(new Date());

        assertEquals(archiveCourse1.equals(archiveCourse2) == archiveCourse2.equals(archiveCourse3),
                archiveCourse3.equals(archiveCourse1));
    }

    @Test
    public void testEqualsCoherence() {
        ArchiveCourse archiveCourse1 = new ArchiveCourse();
        archiveCourse1.setCourse(new Course());
        archiveCourse1.setBeginDate(new Date());
        archiveCourse1.setEndDate(new Date());

        ArchiveCourse archiveCourse2 = new ArchiveCourse();
        archiveCourse2.setCourse(new Course());
        archiveCourse2.setBeginDate(new Date());
        archiveCourse2.setEndDate(new Date());

        assertEquals(archiveCourse1.equals(archiveCourse2), archiveCourse1.equals(archiveCourse2));
    }

    @Test
    public void testEqualsNull() {
        ArchiveCourse archiveCourse1 = new ArchiveCourse();
        archiveCourse1.setCourse(new Course());
        archiveCourse1.setBeginDate(new Date());
        archiveCourse1.setEndDate(new Date());

        ArchiveCourse archiveCourse2 = null;

        assertFalse(archiveCourse1.equals(archiveCourse2));
    }

    @Test
    public void testHashCodeRepeat() {
        ArchiveCourse archiveCourse1 = new ArchiveCourse();
        archiveCourse1.setCourse(new Course());
        archiveCourse1.setBeginDate(new Date());
        archiveCourse1.setEndDate(new Date());
        assertEquals(archiveCourse1.hashCode(), archiveCourse1.hashCode());
    }

    @Test
    public void testHashCodeSame() {
        ArchiveCourse archiveCourse1 = new ArchiveCourse();
        archiveCourse1.setCourse(new Course());
        archiveCourse1.setBeginDate(new Date());
        archiveCourse1.setEndDate(new Date());

        ArchiveCourse archiveCourse2 = new ArchiveCourse();
        archiveCourse2.setCourse(new Course());
        archiveCourse2.setBeginDate(new Date());
        archiveCourse2.setEndDate(new Date());

        assertEquals(archiveCourse1.hashCode(), archiveCourse2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        ArchiveCourse archiveCourse1 = new ArchiveCourse();
        archiveCourse1.setCourse(new Course());
        archiveCourse1.setBeginDate(new Date());
        archiveCourse1.setEndDate(new Date());

        ArchiveCourse archiveCourse2 = new ArchiveCourse();
        archiveCourse2.setCourse(new Course());
        archiveCourse2.getCourse().setDescription("description");
        archiveCourse2.setBeginDate(new Date());
        archiveCourse2.setEndDate(new Date());
        assertNotEquals(archiveCourse1.hashCode(), archiveCourse2.hashCode());
    }
}