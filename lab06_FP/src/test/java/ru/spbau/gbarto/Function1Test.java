package ru.spbau.gbarto;

import org.junit.Test;

import static org.junit.Assert.*;

public class Function1Test {
    @Test
    public void compose() throws Exception {
        Function1<Integer, Integer> f = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x * x;
            }
        };

        Function1<Integer, Integer> g = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x - 4;
            }
        };

        assertEquals(-3,   f.compose(g).apply(1).intValue());
        assertEquals(96,   f.compose(g).apply(10).intValue());
        assertEquals(9996, f.compose(g).apply(100).intValue());
        assertEquals(9,    g.compose(f).apply(1).intValue());
        assertEquals(36,   g.compose(f).apply(10).intValue());
        assertEquals(9216, g.compose(f).apply(100).intValue());
    }
}