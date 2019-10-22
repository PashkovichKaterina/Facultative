package by.trjava.pashkovich.facultative.entity;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CurrentCourseTest {
    @Test
    public void testEqualsReflexivity() {
        CurrentCourse currentCourse = new CurrentCourse();
        currentCourse.setCourse(new Course());
        currentCourse.setBeginDate(new Date());
        currentCourse.setMark(8);
        assertTrue(currentCourse.equals(currentCourse));
    }

    @Test
    public void testEqualsSymmetry() {
        CurrentCourse currentCourse1 = new CurrentCourse();
        currentCourse1.setCourse(new Course());
        currentCourse1.setBeginDate(new Date());
        currentCourse1.setMark(8);

        CurrentCourse currentCourse2 = new CurrentCourse();
        currentCourse2.setCourse(new Course());
        currentCourse2.setBeginDate(new Date());
        currentCourse2.setMark(8);

        assertEquals(currentCourse1.equals(currentCourse2), currentCourse2.equals(currentCourse1));
    }

    @Test
    public void testEqualsTransitivity() {
        CurrentCourse currentCourse1 = new CurrentCourse();
        currentCourse1.setCourse(new Course());
        currentCourse1.setBeginDate(new Date());
        currentCourse1.setMark(8);

        CurrentCourse currentCourse2 = new CurrentCourse();
        currentCourse2.setCourse(new Course());
        currentCourse2.setBeginDate(new Date());
        currentCourse2.setMark(8);

        CurrentCourse currentCourse3 = new CurrentCourse();
        currentCourse3.setCourse(new Course());
        currentCourse3.setBeginDate(new Date());
        currentCourse3.setMark(8);

        assertEquals(currentCourse1.equals(currentCourse2) == currentCourse2.equals(currentCourse3),
                currentCourse3.equals(currentCourse1));
    }

    @Test
    public void testEqualsCoherence() {
        CurrentCourse currentCourse1 = new CurrentCourse();
        currentCourse1.setCourse(new Course());
        currentCourse1.setBeginDate(new Date());
        currentCourse1.setMark(8);

        CurrentCourse currentCourse2 = new CurrentCourse();
        currentCourse2.setCourse(new Course());
        currentCourse2.setBeginDate(new Date());
        currentCourse2.setMark(8);

        assertEquals(currentCourse1.equals(currentCourse2), currentCourse1.equals(currentCourse2));
    }

    @Test
    public void testEqualsNull() {
        CurrentCourse currentCourse1 = new CurrentCourse();
        currentCourse1.setCourse(new Course());
        currentCourse1.setBeginDate(new Date());
        currentCourse1.setMark(8);

        CurrentCourse currentCourse2 = null;

        assertFalse(currentCourse1.equals(currentCourse2));
    }

    @Test
    public void testHashCodeRepeat() {
        CurrentCourse currentCourse1 = new CurrentCourse();
        currentCourse1.setCourse(new Course());
        currentCourse1.setBeginDate(new Date());
        currentCourse1.setMark(8);
        assertEquals(currentCourse1.hashCode(), currentCourse1.hashCode());
    }

    @Test
    public void testHashCodeSame() {
        CurrentCourse currentCourse1 = new CurrentCourse();
        currentCourse1.setCourse(new Course());
        currentCourse1.setBeginDate(new Date());
        currentCourse1.setMark(8);

        CurrentCourse currentCourse2 = new CurrentCourse();
        currentCourse2.setCourse(new Course());
        currentCourse2.setBeginDate(new Date());
        currentCourse2.setMark(8);

        assertEquals(currentCourse1.hashCode(), currentCourse2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        CurrentCourse currentCourse1 = new CurrentCourse();
        currentCourse1.setCourse(new Course());
        currentCourse1.setBeginDate(new Date());
        currentCourse1.setMark(8);

        CurrentCourse currentCourse2 = new CurrentCourse();
        currentCourse2.setCourse(new Course());
        currentCourse2.setBeginDate(new Date());
        currentCourse2.setMark(5);
        assertNotEquals(currentCourse1.hashCode(), currentCourse2.hashCode());
    }
}