package ru.spbau.gbarto;

import org.junit.Test;

import static org.junit.Assert.*;

public class Function2Test {
    private Function2<Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer x, Integer y) {
            return x * x - y * y;
        }
    };

    private Function1<Integer, Integer> g = new Function1<Integer, Integer>() {
        @Override
        public Integer apply(Integer x) {
            return x - 4;
        }
    };

    @Test
    public void compose() throws Exception {
        assertEquals(-4, f.compose(g).apply(1, 1).intValue());
        assertEquals(-103, f.compose(g).apply(1, 10).intValue());
        assertEquals(95, f.compose(g).apply(10, 1).intValue());
        assertEquals(-4, f.compose(g).apply(10, 10).intValue());
    }

    @Test
    public void bind1() throws Exception {
        assertEquals(0, f.bind1(1).apply(1).intValue());
        assertEquals(-99, f.bind1(1).apply(10).intValue());
        assertEquals(99, f.bind1(10).apply(1).intValue());
        assertEquals(0, f.bind1(10).apply(10).intValue());
    }

    @Test
    public void bind2() throws Exception {
        assertEquals(0, f.bind2(1).apply(1).intValue());
        assertEquals(99, f.bind2(1).apply(10).intValue());
        assertEquals(-99, f.bind2(10).apply(1).intValue());
        assertEquals(0, f.bind2(10).apply(10).intValue());
    }

    @Test
    public void curry() throws Exception {
        assertEquals(0, f.curry(1).apply(1).intValue());
        assertEquals(99, f.curry(1).apply(10).intValue());
        assertEquals(-99, f.curry(10).apply(1).intValue());
        assertEquals(0, f.curry(10).apply(10).intValue());
    }

}