package by.trjava.pashkovich.facultative.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplyTest {
    @Test
    public void testEqualsReflexivity() {
        Apply apply = new Apply();
        apply.setCourse(new Course());
        apply.setUser(new User());
        apply.setStatus("status");
        assertTrue(apply.equals(apply));
    }

    @Test
    public void testEqualsSymmetry() {
        Apply apply1 = new Apply();
        apply1.setCourse(new Course());
        apply1.setUser(new User());
        apply1.setStatus("status");

        Apply apply2 = new Apply();
        apply2.setCourse(new Course());
        apply2.setUser(new User());
        apply2.setStatus("status");

        assertEquals(apply1.equals(apply2), apply2.equals(apply1));
    }

    @Test
    public void testEqualsTransitivity() {
        Apply apply1 = new Apply();
        apply1.setCourse(new Course());
        apply1.setUser(new User());
        apply1.setStatus("status");

        Apply apply2 = new Apply();
        apply2.setCourse(new Course());
        apply2.setUser(new User());
        apply2.setStatus("status");

        Apply apply3 = new Apply();
        apply3.setCourse(new Course());
        apply3.setUser(new User());
        apply3.setStatus("status");

        assertEquals(apply1.equals(apply2) == apply2.equals(apply3),
                apply3.equals(apply1));
    }

    @Test
    public void testEqualsCoherence() {
        Apply apply1 = new Apply();
        apply1.setCourse(new Course());
        apply1.setUser(new User());
        apply1.setStatus("status");

        Apply apply2 = new Apply();
        apply2.setCourse(new Course());
        apply2.setUser(new User());
        apply2.setStatus("status");

        assertEquals(apply1.equals(apply2), apply1.equals(apply2));
    }

    @Test
    public void testEqualsNull() {
        Apply apply1 = new Apply();
        apply1.setCourse(new Course());
        apply1.setUser(new User());
        apply1.setStatus("status");

        Apply apply2 = null;

        assertFalse(apply1.equals(apply2));
    }

    @Test
    public void testHashCodeRepeat() {
        Apply apply1 = new Apply();
        apply1.setCourse(new Course());
        apply1.setUser(new User());
        apply1.setStatus("status");
        assertEquals(apply1.hashCode(), apply1.hashCode());
    }

    @Test
    public void testHashCodeSame() {
        Apply apply1 = new Apply();
        apply1.setCourse(new Course());
        apply1.setUser(new User());
        apply1.setStatus("status");

        Apply apply2 = new Apply();
        apply2.setCourse(new Course());
        apply2.setUser(new User());
        apply2.setStatus("status");

        assertEquals(apply1.hashCode(), apply2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        Apply apply1 = new Apply();
        apply1.setCourse(new Course());
        apply1.setUser(new User());
        apply1.setStatus("status");

        Apply apply2 = new Apply();
        apply2.setCourse(new Course());
        apply2.setUser(new User());
        apply2.setStatus("status2");
        assertNotEquals(apply1.hashCode(), apply2.hashCode());
    }
}