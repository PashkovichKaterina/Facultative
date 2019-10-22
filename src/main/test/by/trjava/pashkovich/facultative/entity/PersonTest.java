package by.trjava.pashkovich.facultative.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void testEqualsReflexivity() {
        Person person = new Person();
        person.setSurname("surname");
        person.setName("name");
        person.setPatronymic("patronymic");
        person.setPhone("phone");
        assertTrue(person.equals(person));
    }

    @Test
    public void testEqualsSymmetry() {
        Person person1 = new Person();
        person1.setSurname("surname");
        person1.setName("name");
        person1.setPatronymic("patronymic");
        person1.setPhone("phone");

        Person person2 = new Person();
        person2.setSurname("surname");
        person2.setName("name");
        person2.setPatronymic("patronymic");
        person2.setPhone("phone");

        assertEquals(person1.equals(person2), person2.equals(person1));
    }

    @Test
    public void testEqualsTransitivity() {
        Person person1 = new Person();
        person1.setSurname("surname");
        person1.setName("name");
        person1.setPatronymic("patronymic");
        person1.setPhone("phone");

        Person person2 = new Person();
        person2.setSurname("surname");
        person2.setName("name");
        person2.setPatronymic("patronymic");
        person2.setPhone("phone");

        Person person3 = new Person();
        person3.setSurname("surname");
        person3.setName("name");
        person3.setPatronymic("patronymic");
        person3.setPhone("phone");

        assertEquals(person1.equals(person2) == person2.equals(person3), person3.equals(person1));
    }

    @Test
    public void testEqualsCoherence() {
        Person person1 = new Person();
        person1.setSurname("surname");
        person1.setName("name");
        person1.setPatronymic("patronymic");
        person1.setPhone("phone");

        Person person2 = new Person();
        person2.setSurname("surname");
        person2.setName("name");
        person2.setPatronymic("patronymic");
        person2.setPhone("phone");

        assertEquals(person1.equals(person2), person1.equals(person2));
    }

    @Test
    public void testEqualsNull() {
        Person person1 = new Person();
        person1.setSurname("surname");
        person1.setName("name");
        person1.setPatronymic("patronymic");
        person1.setPhone("phone");

        Person person2 = null;

        assertFalse(person1.equals(person2));
    }

    @Test
    public void testHashCodeRepeat() {
        Person person1 = new Person();
        person1.setSurname("surname");
        person1.setName("name");
        person1.setPatronymic("patronymic");
        person1.setPhone("phone");
        assertEquals(person1.hashCode(), person1.hashCode());
    }

    @Test
    public void testHashCodeSame() {
        Person person1 = new Person();
        person1.setSurname("surname");
        person1.setName("name");
        person1.setPatronymic("patronymic");
        person1.setPhone("phone");

        Person person2 = new Person();
        person2.setSurname("surname");
        person2.setName("name");
        person2.setPatronymic("patronymic");
        person2.setPhone("phone");

        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        Person person1 = new Person();
        person1.setSurname("surname");
        person1.setName("name");
        person1.setPatronymic("patronymic");
        person1.setPhone("phone");

        Person person2 = new Person();
        person2.setSurname("surname2");
        person2.setName("name2");
        person2.setPatronymic("patronymic2");
        person2.setPhone("phone2");
        assertNotEquals(person1.hashCode(), person2.hashCode());
    }
}