package ru.spbau.gbarto;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Function;

import static org.junit.Assert.*;

public class MaybeTest {
    /**
     * Checks correctness of just for Integer 1.
     *
     * @throws MaybeException
     */
    @Test
    public void checkJustInteger() throws MaybeException  {
        Maybe<Integer> mb = Maybe.just(1);
        assertEquals(Integer.valueOf(1), mb.get());
    }

    /**
     * Checks correctness isPresent for Integer 1.
     *
     * @throws MaybeException
     */
    @Test
    public void checkIsPresentInteger() throws MaybeException {
        Maybe<Integer> mb = Maybe.just(1);
        assertTrue(mb.isPresent());
    }

    /**
     * Checks correctness isPresent for nothing.
     *
     * @throws MaybeException
     */
    @Test
    public void checkIsPresentNothing() throws MaybeException {
        Maybe<Integer> mb = Maybe.nothing();
        assertFalse(mb.isPresent());
    }

    /**
     * Checks correctness map for function Integer.sum.
     *
     * @throws MaybeException
     */
    @Test
    public void checkMapIntegerSum() throws MaybeException {
        Maybe<Integer> mb = Maybe.just(1);
        Maybe<Integer> mbr = mb.map((a) -> Integer.sum(a, 2));
        assertEquals(Integer.valueOf(3), mbr.get());
    }

    /**
     * Checks correctness main for generated input.
     *
     * @throws MaybeException
     */
    @Test
    public void checkMainCorrectness() throws MaybeException {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(Main.FILE_IN))){
            for (int i = 5; i >= 1; i--){
                out.println(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Main.main(new String[]{});
        try (Scanner in = new Scanner(new FileInputStream(Main.FILE_OUT));) {
            int[] array = new int[5];
            for (int i = 0; i < 5; i++){
                array[i] = in.nextInt();
            }
            assertArrayEquals(array, new int[]{25, 16, 9, 4, 1});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks correctness map for nothing.
     *
     * @throws MaybeException
     */
    @Test
    public void checkMapNothingIdentity() throws MaybeException {
        assertEquals(Maybe.nothing().map(Function.identity()), Maybe.nothing());
    }

    /**
     * Checks correctness throwing exception in get for nothing.
     *
     * @throws MaybeException
     */
    @Test
    public void checkThrowsException() throws MaybeException {
        Maybe<Integer> mb = Maybe.nothing();
        boolean flag = false;
        try {
            Integer x = mb.get();
        } catch (MaybeException e) {
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Checks correctness get for just Integer 1.
     *
     * @throws MaybeException
     */
    @Test
    public void checkGetInteger() throws MaybeException {
        Maybe<Integer> mb = Maybe.just(1);
        Integer x = mb.get();
        assertEquals(x, (Integer) 1);
    }
}