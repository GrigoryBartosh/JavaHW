package ru.spbau.gbarto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * An example of work of the Tree
 */
public class Main {
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        Trie t = new Trie();
        System.out.println("add \"aaaa\": " + t.add("aaaa"));
        System.out.println("add \"abaa\": " + t.add("abaa"));
        System.out.println(".add \"abca\": " + t.add("abca"));

        System.out.println("size: " + t.size());

        System.out.println("remove \"abca\": " + t.remove("abca"));
        System.out.println("size: " + t.size());
        System.out.println("remove \"zzzz\": " + t.remove("zzzz"));

        System.out.println("howManyStartsWithPrefix \"a\": " + t.howManyStartsWithPrefix("a"));
        System.out.println("howManyStartsWithPrefix \"ab\": " +  t.howManyStartsWithPrefix("ab"));

        System.out.println("contains \"aa\": " + t.contains("aa"));
        System.out.println("contains \"aaaa\": " + t.contains("aaa"));
    }
}