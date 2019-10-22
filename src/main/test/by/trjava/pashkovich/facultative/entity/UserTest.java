package by.trjava.pashkovich.facultative.entity;

import by.trjava.pashkovich.facultative.entity.characteristic.UserRole;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testEqualsReflexivity() {
        User user = new User();
        user.setLogin("login");
        user.setId(5);
        user.setEmail("email");
        user.setPassword("password");
        user.setRole(UserRole.STUDENT);
        assertTrue(user.equals(user));
    }

    @Test
    public void testEqualsSymmetry() {
        User user1 = new User();
        user1.setLogin("login");
        user1.setId(5);
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setRole(UserRole.STUDENT);

        User user2 = new User();
        user2.setLogin("login");
        user2.setId(5);
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setRole(UserRole.STUDENT);

        assertEquals(user1.equals(user2), user2.equals(user1));
    }

    @Test
    public void testEqualsTransitivity() {
        User user1 = new User();
        user1.setLogin("login");
        user1.setId(5);
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setRole(UserRole.STUDENT);

        User user2 = new User();
        user2.setLogin("login");
        user2.setId(5);
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setRole(UserRole.STUDENT);

        User user3 = new User();
        user3.setLogin("login");
        user3.setId(5);
        user3.setEmail("email");
        user3.setPassword("password");
        user3.setRole(UserRole.STUDENT);

        assertEquals(user1.equals(user2) == user2.equals(user3), user3.equals(user1));
    }

    @Test
    public void testEqualsCoherence() {
        User user1 = new User();
        user1.setLogin("login");
        user1.setId(5);
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setRole(UserRole.STUDENT);

        User user2 = new User();
        user2.setLogin("login");
        user2.setId(5);
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setRole(UserRole.STUDENT);

        assertEquals(user1.equals(user2), user1.equals(user2));
    }

    @Test
    public void testEqualsNull() {
        User user1 = new User();
        user1.setLogin("login");
        user1.setId(5);
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setRole(UserRole.STUDENT);

        User user2 = null;
        assertFalse(user1.equals(user2));
    }

    @Test
    public void testHashCodeRepeat() {
        User user1 = new User();
        user1.setLogin("login");
        user1.setId(5);
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setRole(UserRole.STUDENT);
        assertEquals(user1.hashCode(), user1.hashCode());
    }

    @Test
    public void testHashCodeSame() {
        User user1 = new User();
        user1.setLogin("login");
        user1.setId(5);
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setRole(UserRole.STUDENT);

        User user2 = new User();
        user2.setLogin("login");
        user2.setId(5);
        user2.setEmail("email");
        user2.setPassword("password");
        user2.setRole(UserRole.STUDENT);

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        User user1 = new User();
        user1.setLogin("login");
        user1.setId(5);
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setRole(UserRole.STUDENT);

        User user2 = new User();
        user2.setLogin("login2");
        user2.setId(6);
        user2.setEmail("email3");
        user2.setPassword("password4");
        user2.setRole(UserRole.TEACHER);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }
}