package ru.spbau.gbarto;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {
    /**
     * Checks the order for two pairs.
     *
     * @throws Exception
     */
    @Test
    public void getHead() throws Exception {
        LinkedList list = new LinkedList();
        list.add("a", "b");
        list.add("b", "a");
        LinkedList.Pair p = list.getHead().getData();
        assertEquals("a", p.getKey());
        assertEquals("b", p.getValue());
    }

    /**
     * Puts five pairs in the LinkedList. Then checks what keys are contained in the LinkedList.
     *
     * @throws Exception
     */
    @Test
    public void contains() throws Exception {
        LinkedList list = new LinkedList();
        list.add("a", "1");
        list.add("b", "2");
        list.add("c", "3");
        list.add("d", "4");
        list.add("e", "5");
        assertEquals(true, list.contains("c"));
        assertEquals(false, list.contains("z"));
    }

    /**
     * Puts five pairs in the LinkedList. Tries two times to get the value by key.
     *
     * @throws Exception
     */
    @Test
    public void get() throws Exception {
        LinkedList list = new LinkedList();
        list.add("a", "1");
        list.add("b", "2");
        list.add("c", "3");
        list.add("d", "4");
        list.add("e", "5");
        assertEquals("3", list.get("c"));
        assertEquals(null, list.get("z"));
    }

    /**
     * Puts two pairs with same keys in the LinkedList. Takes out the first key.
     *
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        LinkedList list = new LinkedList();
        list.add("a", "1");
        list.add("a", "2");
        assertEquals("1", list.get("a"));
    }

    /**
     * Removes one of two pairs with same keys.
     *
     * @throws Exception
     */
    @Test
    public void remove() throws Exception {
        LinkedList list = new LinkedList();
        list.add("a", "1");
        list.add("a", "2");
        list.remove("a");
        assertEquals("2", list.get("a"));
    }

    /**
     * Tries to get value by key from cleared LinkedList.
     *
     * @throws Exception
     */
    @Test
    public void clear() throws Exception {
        LinkedList list = new LinkedList();
        list.add("a", "1");
        list.add("a", "2");
        list.clear();
        assertEquals(null, list.get("a"));
    }

}