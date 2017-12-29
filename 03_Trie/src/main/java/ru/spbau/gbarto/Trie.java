package ru.spbau.gbarto;

import java.io.*;
import java.util.HashMap;

/**
 * The implementation of the trie.
 * Allows you to add strings, delete strings, check the number of strings.
 * A structure can be serialized.
 */
public class Trie implements Serializable {
    private Node root = new Node();

    /**
     * Structure for storing information about a node.
     */
    private class Node implements Serializable {
        private HashMap<Character, Node> table = new HashMap<>();
        private boolean isEmpty = true;
        private int size = 0;

        private Node move(char c) {
            if (!table.containsKey(c)) {
                table.put(c, new Node());
            }
            return table.get(c);
        }
    }

    /**
     * Checks that Trie contains given String.
     *
     * @param str a string to check
     * @return true if Trie contains specified string, false otherwise
     */
    public boolean contains(String str) {
        Node n = root;
        for (int i = 0; i < str.length() && n.size > 0; i++) {
            char c = str.charAt(i);
            n = n.move(c);
        }
        return !n.isEmpty;
    }

    /**
     * Adds string to Trie. Does nothing, if there such string exists.
     *
     * @param str a string to add into the Trie
     * @return true if added a string, false otherwise
     */
    public boolean add(String str) {
        if (contains(str)) {
            return false;
        }

        Node n = root;
        for (int i = 0; i < str.length(); i++) {
            n.size++;
            char c = str.charAt(i);
            n = n.move(c);
        }
        n.size++;
        n.isEmpty = false;
        return true;
    }

    /**
     * Removes string from Trie. Does nothing, if there is no string in the Trie.
     *
     * @param str a string to remove from Trie
     * @return true if removed a string, false otherwise
     */
    public boolean remove(String str) {
        if (!contains(str)) {
            return false;
        }

        Node n = root;
        for (int i = 0; i < str.length(); i++) {
            n.size--;
            char c = str.charAt(i);
            n = n.move(c);
        }
        n.size--;
        n.isEmpty = true;
        return true;
    }

    /**
     * Get number of strings in the Trie.
     *
     * @return a number of strings in the Trie.
     */
    public int size() {
        return root.size;
    }

    /**
     * Returns a number of strings which start with the given prefix.
     *
     * @param str a prefix needed to be checked
     * @return number of Strings in the Trie which start with prefix
     */
    public int howManyStartsWithPrefix(String str) {
        Node n = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            n = n.move(c);
        }
        return n.size;
    }

    /**
     *  Writes a Trie into the output stream.
     *
     * @param out an output stream to write into
     * @throws IOException if couldn't write to the given output stream
     */
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(this);
        os.close();
    }

    /**
     * Reads a Trie from the input stream.
     *
     * @param in an input stream to read from
     * @throws IOException if couldn't read to the given output stream
     * @throws ClassNotFoundException if tries to read something which is not a Trie
     */
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(in);
        Trie t = (Trie) is.readObject();
        root = t.root;
        is.close();
    }
}
