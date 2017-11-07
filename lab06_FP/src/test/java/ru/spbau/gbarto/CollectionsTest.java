package ru.spbau.gbarto;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.pow;
import static org.junit.Assert.*;

public class CollectionsTest {
    @Test
    public void map() throws Exception {
        Function1<Integer, Integer> f = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x * x;
            }
        };

        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1, 10, 100));
        ArrayList<Integer> nl = Collections.map(f, l);

        assertEquals( new ArrayList<>(Arrays.asList(1, 100, 10000)), nl);
    }

    @Test
    public void filter() throws Exception {
        Predicate<Integer> f = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x * x < 1000;
            }
        };

        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1, 10, 100));
        ArrayList<Integer> nl = Collections.filter(f, l);

        assertEquals( new ArrayList<>(Arrays.asList(1, 10)), nl);
    }

    @Test
    public void takeWhile() throws Exception {
        Predicate<Integer> f = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x * x < 1000;
            }
        };

        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1, 10, 100, 10, 1));
        ArrayList<Integer> nl = Collections.takeWhile(f, l);

        assertEquals( new ArrayList<>(Arrays.asList(1, 10)), nl);
    }

    @Test
    public void takeUnless() throws Exception {
        Predicate<Integer> f = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer x) {
                return x * x < 1000;
            }
        };

        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(100, 10, 1));
        ArrayList<Integer> nl = Collections.takeUnless(f, l);

        assertEquals( new ArrayList<>(Arrays.asList(100)), nl);
    }

    @Test
    public void foldr() throws Exception {
        Function2<Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return (int)pow(x, y);
            }
        };

        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(10, 100, 10));
        int res = Collections.foldr(f, -7, l);

        assertEquals(10, res);
    }

    @Test
    public void foldl() throws Exception {
        Function2<Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return (int)pow(x, y);
            }
        };

        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(10, 100, 10));
        int res = Collections.foldl(f, -7, l);

        assertEquals(2147483647, res);
    }

}