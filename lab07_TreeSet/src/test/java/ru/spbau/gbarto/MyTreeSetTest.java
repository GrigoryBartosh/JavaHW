package ru.spbau.gbarto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MyTreeSetTest {
    private MyTreeSet<Integer> st;

    @BeforeEach
    void setUpSets() {
        st = new MyTreeSet<>();
    }

    @Test
    void testSize() {
        MyTreeSet<Integer> st = new MyTreeSet<Integer>();
        assertEquals(0, st.size());
        st.add(1);
        st.add(2);
        assertEquals(2, st.size());
        st.add(2);
        assertEquals(2, st.size());
    }

    @Test
    void testIsEmpty() {
        MyTreeSet<Integer> st = new MyTreeSet<Integer>();
        assertTrue(st.isEmpty());
        st.add(1);
        assertFalse(st.isEmpty());
        st.remove(1);
        assertTrue(st.isEmpty());
    }

    @Test
    void testAdd() {
        MyTreeSet<Integer> st = new MyTreeSet<Integer>();
        st.add(8);
        st.add(8);
        st.add(0);
        st.add(0);
        st.add(5);
        st.add(5);
        st.add(5);
        st.add(3);
        st.add(5);
        st.add(3);
        st.add(5);

        assertEquals("[0, 3, 5, 8]", st.toString());
    }

    @Test
    void testAddCmp() {
        MyTreeSet<Integer> st = new MyTreeSet<>((a, b) -> b - a);
        st.add(8);
        st.add(8);
        st.add(0);
        st.add(0);
        st.add(5);
        st.add(5);
        st.add(5);
        st.add(3);
        st.add(5);
        st.add(3);
        st.add(5);

        assertEquals("[8, 5, 3, 0]", st.toString());
    }

    @Test
    void testContains() {
        MyTreeSet<Integer> st = new MyTreeSet<Integer>();
        st.add(8);
        st.add(8);
        st.add(0);
        st.add(0);
        st.add(5);
        st.add(5);
        st.add(5);
        st.add(3);
        st.add(5);
        st.add(3);
        st.add(5);
        assertTrue(st.contains(0));
        assertFalse(st.contains(1));
        assertFalse(st.contains(2));
        assertTrue(st.contains(3));
        assertFalse(st.contains(4));
        assertTrue(st.contains(5));
        assertFalse(st.contains(6));
        assertTrue(st.contains(8));
        st.remove(1);
        st.remove(8);
        st.remove(6);
        st.remove(5);
        assertTrue(st.contains(0));
        assertFalse(st.contains(1));
        assertFalse(st.contains(2));
        assertTrue(st.contains(3));
        assertFalse(st.contains(4));
        assertFalse(st.contains(5));
        assertFalse(st.contains(6));
        assertFalse(st.contains(8));
    }

    @Test
    void testIteratorSet() {
        st.addAll(Arrays.asList(5, 1, 4, 3, 2));
        Iterator<Integer> iterator = st.iterator();
        for (int i = 1; i <= 5; i++) {
            assertTrue(iterator.hasNext());
            assertEquals(i, iterator.next().intValue());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    void testDescendingIteratorSet() {
        st.addAll(Arrays.asList(5, 1, 4, 3, 2));
        Iterator<Integer> iterator = st.descendingIterator();
        for (int i = 5; i >= 1; i--) {
            assertTrue(iterator.hasNext());
            assertEquals(i, iterator.next().intValue());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFirst() {
        st.addAll(Arrays.asList(5, 1, 4, 3, 2));
        assertEquals(1, st.first().intValue());
    }

    @Test
    void testFirstDescendingOrder() {
        st.addAll(Arrays.asList(5, 1, 4, 3, 2));
        assertEquals(5, st.descendingSet().first().intValue());
    }

    @Test
    void testLast() {
        st.addAll(Arrays.asList(5, 1, 4, 3, 2));
        assertEquals(5, st.last().intValue());
    }

    @Test
    void testLastDescendingOrder() {
        st.addAll(Arrays.asList(5, 1, 4, 3, 2));
        assertEquals(1, st.descendingSet().last().intValue());
    }

    @Test
    void testFloorAscendingOrderIntegerSet() {
        st.addAll(Arrays.asList(9, 1, 6, 3, 2));
        assertEquals(3, st.floor(3).intValue());
        assertEquals(3, st.floor(4).intValue());
        assertEquals(3, st.floor(5).intValue());
    }

    @Test
    void testLowerAscendingOrderIntegerSet() {
        st.addAll(Arrays.asList(9, 1, 6, 3, 2));
        assertEquals(2, st.lower(3).intValue());
        assertEquals(3, st.lower(4).intValue());
        assertEquals(3, st.lower(5).intValue());
    }

    @Test
    void testCeilingAscendingOrderIntegerSet() {
        st.addAll(Arrays.asList(9, 1, 6, 3, 2));
        assertEquals(3, st.ceiling(3).intValue());
        assertEquals(6, st.ceiling(4).intValue());
        assertEquals(6, st.ceiling(5).intValue());
    }

    @Test
    void testHigherAscendingOrderIntegerSet() {
        st.addAll(Arrays.asList(9, 1, 6, 3, 2));
        assertEquals(6, st.higher(3).intValue());
        assertEquals(6, st.higher(4).intValue());
        assertEquals(6, st.higher(5).intValue());
    }
}