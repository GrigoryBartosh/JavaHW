package ru.spbau.gbarto;

import org.junit.Test;

import static org.junit.Assert.*;

public class SetTest {
    /**
     * Checks working of size on empty set.
     *
     * @throws Exception
     */
    @Test
    public void sizeEmpty() throws Exception {
        Set<Integer> set = new Set<>();
        assertEquals(set.size(), 0);
    }

    /**
     * Checks working of size on set of 5 elements.
     *
     * @throws Exception
     */
    @Test
    public void sizeFive() throws Exception {
        Set<Integer> set = new Set<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        assertEquals(set.size(), 5);
    }

    /**
     * Checks working of contains on empty set.
     *
     * @throws Exception
     */
    @Test
    public void containsEmpty() throws Exception {
        Set<Integer> set = new Set<>();
        assertFalse(set.contains(0));
        assertFalse(set.contains(1));
        assertFalse(set.contains(5));
    }

    /**
     * Checks working of contains on set of {1, 5, 10, 12, 50}.
     *
     * @throws Exception
     */
    @Test
    public void contains() throws Exception {
        Set<Integer> set = new Set<>();
        set.add(1);
        set.add(5);
        set.add(10);
        set.add(12);
        set.add(50);
        assertTrue(set.contains(1));
        assertTrue(set.contains(10));
        assertTrue(set.contains(50));
        assertFalse(set.contains(2));
        assertFalse(set.contains(6));
        assertFalse(set.contains(21));
    }

    /**
     * Checks working of add with same keys.
     *
     * @throws Exception
     */
    @Test
    public void addSame() throws Exception {
        Set<Integer> set = new Set<>();
        assertTrue(set.add(8));
        assertFalse(set.add(8));
        assertTrue(set.add(0));
        assertFalse(set.add(0));
        assertTrue(set.add(5));
        assertFalse(set.add(5));
        assertFalse(set.add(5));
        assertTrue(set.add(3));
        assertFalse(set.add(5));
        assertFalse(set.add(3));
        assertFalse(set.add(5));
    }

    /**
     * Checks correctnes of beildeing bamboo tree.
     *
     * @throws Exception
     */
    @Test
    public void addBamboo() throws Exception {
        Set<Integer> set = new Set<>();
        assertTrue(set.add(0));
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.add(4));
        assertTrue(set.add(5));
        assertTrue(set.add(6));
        assertFalse(set.add(0));
        assertFalse(set.add(2));
        assertFalse(set.add(4));
        assertFalse(set.add(6));
    }
}