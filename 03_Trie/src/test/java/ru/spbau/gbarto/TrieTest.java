package ru.spbau.gbarto;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class TrieTest {
    /**
     * Adds different strings to Trie then checks contain.
     */
    @Test
    public void containsStringOfA() throws Exception {
        Trie t = new Trie();
        t.add("a");
        t.add("aaa");
        t.add("aaaa");
        t.add("aaaaa");
        assertTrue(t.contains("a"));
        assertFalse(t.contains("aa"));
        assertTrue(t.contains("aaa"));
        assertTrue(t.contains("aaaa"));
        assertTrue(t.contains("aaaaa"));
        assertFalse(t.contains("b"));
        assertFalse(t.contains("c"));
    }

    /**
     * Adds strings to Trie and checks correctness of returning values.
     */
    @Test
    public void addEqualsStrings() throws Exception {
        Trie t = new Trie();
        assertTrue(t.add("a"));
        assertTrue(t.add("aaa"));
        assertTrue(t.add("aaaa"));
        assertTrue(t.add("aaaaa"));
        assertFalse(t.add("aaa"));
        assertTrue(t.add("aa"));
    }

    /**
     * Adds different strings to Trie then removes ans checks correctness of returning values.
     */
    @Test
    public void removeStringNotInTree() throws Exception {
        Trie t = new Trie();
        t.add("a");
        t.add("aaa");
        t.add("aaaa");
        t.add("aaaaa");
        assertFalse(t.remove("b"));
        assertFalse(t.remove("c"));
        assertTrue(t.remove("a"));
        assertFalse(t.remove("aa"));
        assertTrue(t.remove("aaa"));
        assertTrue(t.remove("aaaa"));
        assertTrue(t.remove("aaaaa"));
        assertFalse(t.remove("b"));
        assertFalse(t.remove("c"));
    }

    /**
     * Adds different strings to Trie then checks correctness of size then removes some strings and checks correctness of size again.
     */
    @Test
    public void size() throws Exception {
        Trie t = new Trie();
        t.add("a");
        t.add("aaa");
        t.add("aaaa");
        t.add("aaaaa");
        assertEquals(4, t.size());
        t.remove("aaaa");
        t.remove("aa");
        assertEquals(3, t.size());
    }

    /**
     * Adds different strings and checks how many strings start with prefix.
     */
    @Test
    public void howManyStartsWithPrefix() throws Exception {
        Trie t = new Trie();
        t.add("a");
        t.add("aaa");
        t.add("aaaa");
        t.add("aaaaa");
        assertEquals(4, t.howManyStartsWithPrefix(""));
        assertEquals(4, t.howManyStartsWithPrefix("a"));
        assertEquals(3, t.howManyStartsWithPrefix("aa"));
        assertEquals(3, t.howManyStartsWithPrefix("aaa"));
        assertEquals(2, t.howManyStartsWithPrefix("aaaa"));
        assertEquals(1, t.howManyStartsWithPrefix("aaaaa"));
        assertEquals(0, t.howManyStartsWithPrefix("b"));
        t.remove("aaaa");
        t.remove("aa");
        assertEquals(1, t.howManyStartsWithPrefix("aaaa"));
    }

    /**
     * Outputs the Tree to the stream, then reads the Tree from the stream and checks its parameters.
     */
    @Test
    public void serialize() throws Exception {
        Trie t = new Trie();
        t.add("Somebody once told me");
        t.add("The world is gonna roll me");
        t.add("I ain't the sharpest tool in the shed");
        t.add("She was lookin kinda dumb with her finger and her thumb");
        t.add("In the shape of an \"L\" on her forehead");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        t.serialize(os);
        t = new Trie();
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        t.deserialize(is);

        assertEquals(5, t.size());
        assertTrue(t.contains("Somebody once told me"));
        assertTrue(t.contains("The world is gonna roll me"));
        assertTrue(t.contains("I ain't the sharpest tool in the shed"));
        assertTrue(t.contains("She was lookin kinda dumb with her finger and her thumb"));
        assertTrue(t.contains("In the shape of an \"L\" on her forehead"));
        assertEquals(2, t.howManyStartsWithPrefix("S"));
    }
}