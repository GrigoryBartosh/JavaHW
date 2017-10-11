package ru.spbau.gbarto;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {
    /**
     * Check size for 1, 2 and 3 element.
     *
     * @throws Exception
     */
    @Test
    public void size() throws Exception {
        HashTable table = new HashTable();
        table.put("a", "b");
        assertEquals(1, table.size());
        table.put("b", "c");
        assertEquals(2, table.size());
        table.put("c", "d");
        assertEquals(3, table.size());
    }

    /**
     * Puts three pairs in the HashTable. Tries checks what keys are contained in the HashTable.
     *
     * @throws Exception
     */
    @Test
    public void contains() throws Exception {
        HashTable table = new HashTable();
        table.put("a", "b");
        table.put("b", "c");
        table.put("c", "d");
        assertTrue(table.contains("a"));
        assertTrue(table.contains("c"));
        assertFalse(table.contains("z"));
    }

    /**
     * Puts three pairs in the HashTable. Tries three times to get the value by key.
     *
     * @throws Exception
     */
    @Test
    public void get() throws Exception {
        HashTable table = new HashTable();
        table.put("a", "b");
        table.put("b", "c");
        table.put("c", "d");
        assertEquals("b", table.get("a"));
        assertEquals("d", table.get("c"));
        assertEquals(null, table.get("z"));
    }

    /**
     * Place an element in place of an existing one.
     *
     * @throws Exception
     */
    @Test
    public void put() throws Exception {
        HashTable table = new HashTable();
        table.put("a", "b");
        table.put("b", "c");
        table.put("c", "d");
        table.put("a", "1");
        assertEquals("1", table.get("a"));
    }

    /**
     * Removes an existing and non-existent key.
     *
     * @throws Exception
     */
    @Test
    public void remove() throws Exception {
        HashTable table = new HashTable();
        table.put("a", "b");
        table.put("b", "c");
        table.put("c", "d");
        table.remove("a");
        table.remove("z");
        assertFalse(table.contains("a"));
        assertTrue(table.contains("b"));
        assertTrue(table.contains("c"));
    }

    /**
     * Checks the size of the cleared table.
     *
     * @throws Exception
     */
    @Test
    public void clear() throws Exception {
        HashTable table = new HashTable();
        table.put("a", "b");
        table.put("b", "c");
        table.put("c", "d");
        table.clear();
        assertEquals(0, table.size());
    }

}