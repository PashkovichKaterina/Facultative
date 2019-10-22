package by.trjava.pashkovich.facultative.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {
    @Test
    public void testEqualsReflexivity() {
        Course course = new Course();
        course.setId(2);
        course.setTitle("title");
        course.setTeacher("teacher");
        course.setCategory("category");
        course.setClassesNumber(20);
        course.setAvailability(true);
        course.setDescription("description");
        assertTrue(course.equals(course));
    }

    @Test
    public void testEqualsSymmetry() {
        Course course1 = new Course();
        course1.setId(2);
        course1.setTitle("title");
        course1.setTeacher("teacher");
        course1.setCategory("category");
        course1.setClassesNumber(20);
        course1.setAvailability(true);
        course1.setDescription("description");

        Course course2 = new Course();
        course2.setId(2);
        course2.setTitle("title");
        course2.setTeacher("teacher");
        course2.setCategory("category");
        course2.setClassesNumber(20);
        course2.setAvailability(true);
        course2.setDescription("description");

        assertEquals(course1.equals(course2), course2.equals(course1));
    }

    @Test
    public void testEqualsTransitivity() {
        Course course1 = new Course();
        course1.setId(2);
        course1.setTitle("title");
        course1.setTeacher("teacher");
        course1.setCategory("category");
        course1.setClassesNumber(20);
        course1.setAvailability(true);
        course1.setDescription("description");

        Course course2 = new Course();
        course2.setId(2);
        course2.setTitle("title");
        course2.setTeacher("teacher");
        course2.setCategory("category");
        course2.setClassesNumber(20);
        course2.setAvailability(true);
        course2.setDescription("description");

        Course course3 = new Course();
        course3.setId(2);
        course3.setTitle("title");
        course3.setTeacher("teacher");
        course3.setCategory("category");
        course3.setClassesNumber(20);
        course3.setAvailability(true);
        course3.setDescription("description");

        assertEquals(course1.equals(course2) == course2.equals(course3),
                course3.equals(course1));
    }

    @Test
    public void testEqualsCoherence() {
        Course course1 = new Course();
        course1.setId(2);
        course1.setTitle("title");
        course1.setTeacher("teacher");
        course1.setCategory("category");
        course1.setClassesNumber(20);
        course1.setAvailability(true);
        course1.setDescription("description");

        Course course2 = new Course();
        course2.setId(2);
        course2.setTitle("title");
        course2.setTeacher("teacher");
        course2.setCategory("category");
        course2.setClassesNumber(20);
        course2.setAvailability(true);
        course2.setDescription("description");

        assertEquals(course1.equals(course2), course1.equals(course2));
    }

    @Test
    public void testEqualsNull() {
        Course course1 = new Course();
        course1.setId(2);
        course1.setTitle("title");
        course1.setTeacher("teacher");
        course1.setCategory("category");
        course1.setClassesNumber(20);
        course1.setAvailability(true);
        course1.setDescription("description");

        Course course2 = null;

        assertFalse(course1.equals(course2));
    }

    @Test
    public void testHashCodeRepeat() {
        Course course1 = new Course();
        course1.setId(2);
        course1.setTitle("title");
        course1.setTeacher("teacher");
        course1.setCategory("category");
        course1.setClassesNumber(20);
        course1.setAvailability(true);
        course1.setDescription("description");
        assertEquals(course1.hashCode(), course1.hashCode());
    }

    @Test
    public void testHashCodeSame() {
        Course course1 = new Course();
        course1.setId(2);
        course1.setTitle("title");
        course1.setTeacher("teacher");
        course1.setCategory("category");
        course1.setClassesNumber(20);
        course1.setAvailability(true);
        course1.setDescription("description");

        Course course2 = new Course();
        course2.setId(2);
        course2.setTitle("title");
        course2.setTeacher("teacher");
        course2.setCategory("category");
        course2.setClassesNumber(20);
        course2.setAvailability(true);
        course2.setDescription("description");
        assertEquals(course1.hashCode(), course2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        Course course1 = new Course();
        course1.setId(2);
        course1.setTitle("title");
        course1.setTeacher("teacher");
        course1.setCategory("category");
        course1.setClassesNumber(20);
        course1.setAvailability(true);
        course1.setDescription("description");

        Course course2 = new Course();
        course2.setId(2);
        course2.setTitle("title1");
        course2.setTeacher("teacher1");
        course2.setCategory("category1");
        course2.setClassesNumber(10);
        course2.setAvailability(false);
        course2.setDescription("description2");
        assertNotEquals(course1.hashCode(), course2.hashCode());
    }
}