package by.trjava.pashkovich.facultative.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeacherTest {
    @Test
    public void testEqualsReflexivity() {
        Teacher teacher = new Teacher();
        teacher.setPosition("position");
        assertTrue(teacher.equals(teacher));
    }

    @Test
    public void testEqualsSymmetry() {
        Teacher teacher1 = new Teacher();
        teacher1.setPosition("position");

        Teacher teacher2 = new Teacher();
        teacher2.setPosition("position");

        assertEquals(teacher1.equals(teacher2), teacher2.equals(teacher1));
    }

    @Test
    public void testEqualsTransitivity() {
        Teacher teacher1 = new Teacher();
        teacher1.setPosition("position");

        Teacher teacher2 = new Teacher();
        teacher2.setPosition("position");

        Teacher teacher3 = new Teacher();
        teacher3.setPosition("position");

        assertEquals(teacher1.equals(teacher2) == teacher2.equals(teacher3), teacher3.equals(teacher1));
    }

    @Test
    public void testEqualsCoherence() {
        Teacher teacher1 = new Teacher();
        teacher1.setPosition("position");

        Teacher teacher2 = new Teacher();
        teacher2.setPosition("position");

        assertEquals(teacher1.equals(teacher2), teacher1.equals(teacher2));
    }

    @Test
    public void testEqualsNull() {
        Teacher teacher1 = new Teacher();
        teacher1.setPosition("position");

        Teacher teacher2 = null;

        assertFalse(teacher1.equals(teacher2));
    }

    @Test
    public void testHashCodeRepeat() {
        Teacher teacher1 = new Teacher();
        teacher1.setPosition("position");
        assertEquals(teacher1.hashCode(), teacher1.hashCode());
    }

    @Test
    public void testHashCodeSame() {
        Teacher teacher1 = new Teacher();
        teacher1.setPosition("position");

        Teacher teacher2 = new Teacher();
        teacher2.setPosition("position");

        assertEquals(teacher1.hashCode(), teacher2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        Teacher teacher1 = new Teacher();
        teacher1.setPosition("position1");

        Teacher teacher2 = new Teacher();
        teacher2.setPosition("position2");
        assertNotEquals(teacher1.hashCode(), teacher2.hashCode());
    }
}