package by.trjava.pashkovich.facultative.entity;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void testEqualsReflexivity() {
        Student student = new Student();
        student.setAddress("address");
        student.setDateOfBirth(new Date());
        assertTrue(student.equals(student));
    }

    @Test
    public void testEqualsSymmetry() {
        Student student1 = new Student();
        student1.setAddress("address");
        student1.setDateOfBirth(new Date());

        Student student2 = new Student();
        student2.setAddress("address");
        student2.setDateOfBirth(new Date());

        assertEquals(student1.equals(student2), student2.equals(student1));
    }

    @Test
    public void testEqualsTransitivity() {
        Student student1 = new Student();
        student1.setAddress("address");
        student1.setDateOfBirth(new Date());

        Student student2 = new Student();
        student2.setAddress("address");
        student2.setDateOfBirth(new Date());

        Student student3 = new Student();
        student3.setAddress("address");
        student3.setDateOfBirth(new Date());

        assertEquals(student1.equals(student2) == student2.equals(student3), student3.equals(student1));
    }

    @Test
    public void testEqualsCoherence() {
        Student student1 = new Student();
        student1.setAddress("address");
        student1.setDateOfBirth(new Date());

        Student student2 = new Student();
        student2.setAddress("address");
        student2.setDateOfBirth(new Date());

        assertEquals(student1.equals(student2), student1.equals(student2));
    }

    @Test
    public void testEqualsNull() {
        Student student1 = new Student();
        student1.setAddress("address");
        student1.setDateOfBirth(new Date());

        Student student2 = null;

        assertFalse(student1.equals(student2));
    }

    @Test
    public void testHashCodeRepeat() {
        Student student1 = new Student();
        student1.setAddress("address");
        student1.setDateOfBirth(new Date());
        assertEquals(student1.hashCode(), student1.hashCode());
    }

    @Test
    public void testHashCodeSame() {
        Student student1 = new Student();
        student1.setAddress("address");
        student1.setDateOfBirth(new Date());

        Student student2 = new Student();
        student2.setAddress("address");
        student2.setDateOfBirth(new Date());

        assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        Student student1 = new Student();
        student1.setAddress("address");
        student1.setDateOfBirth(new Date());

        Student student2 = new Student();
        student2.setAddress("address2");
        student2.setDateOfBirth(new Date());
        assertNotEquals(student1.hashCode(), student2.hashCode());
    }
}