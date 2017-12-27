package ru.spbau.gbarto;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaybeTest {
    /**
     * Checks correctness of just for Integer 1.
     *
     * @throws MaybeException
     */
    @Test
    public void just() throws MaybeException  {
        Maybe<Integer> mb = Maybe.just(1);
        assertEquals(Integer.valueOf(1), mb.get());
    }

    /**
     * Checks correctness isPresent for Integer 1.
     *
     * @throws MaybeException
     */
    @Test
    public void isPresent() throws MaybeException {
        Maybe<Integer> mb = Maybe.just(1);
        assertTrue(mb.isPresent());
    }

    /**
     * Checks correctness isPresent for nothing.
     *
     * @throws MaybeException
     */
    @Test
    public void isPresentNothing() throws MaybeException {
        Maybe<Integer> mb = Maybe.nothing();
        assertFalse(mb.isPresent());
    }

    /**
     * Checks correctness map for function Integer.sum.
     *
     * @throws MaybeException
     */
    @Test
    public void map() throws MaybeException {
        Maybe<Integer> mb = Maybe.just(1);
        Maybe<Integer> mbr = mb.map((a) -> Integer.sum(a, 2));
        assertEquals(Integer.valueOf(3), mbr.get());
    }

}