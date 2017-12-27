package ru.spbau.gbarto;

import org.junit.Test;

import static org.junit.Assert.*;

public class PredicateTest {
    private Predicate<Integer> f = new Predicate<Integer>() {
        @Override
        public Boolean apply(Integer x) {
            return x < 5;
        }
    };

    private Predicate<Integer> g = new Predicate<Integer>() {
        @Override
        public Boolean apply(Integer x) {
            return x % 2 == 0;
        }
    };

    private Predicate<Integer> h = new Predicate<Integer>() {
        @Override
        public Boolean apply(Integer x) {
            return x > 9 && x < 11;
        }
    };

    @Test
    public void or() throws Exception {
        assertTrue(f.or(g).apply(4));
        assertFalse(f.or(h).apply(5));
        assertFalse(g.or(h).apply(5));
        assertTrue(f.or(g).or(h).apply(1));
        assertTrue(f.or(g).or(h).apply(10));
        assertTrue(f.or(g).or(h).apply(100));
    }

    @Test
    public void and() throws Exception {
        assertTrue(f.and(g).apply(4));
        assertFalse(f.and(h).apply(5));
        assertFalse(g.and(h).apply(5));
        assertFalse(f.and(g).or(h).apply(1));
        assertTrue(f.and(g).or(h).apply(10));
        assertFalse(f.and(g).or(h).apply(100));
    }

    @Test
    public void not() throws Exception {
        assertFalse(f.not().apply(1));
        assertTrue(f.not().apply(10));
        assertTrue(g.not().apply(1));
        assertFalse(g.not().apply(10));
        assertTrue(h.not().apply(1));
        assertFalse(h.not().apply(10));
    }

}