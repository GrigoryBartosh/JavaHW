package ru.spbau.gbarto;

import com.sun.istack.internal.NotNull;

/**
 * Set is a binary search tree storing keys.
 *
 * @param <T> type of key
 */
public class Set<T extends Comparable<? super T>> {
    private Node root = null;

    /**
     * Node is a auxiliary class. Stores key, size of the subtree and both children.
     */
    private class Node {
        private Node left = null;
        private Node right = null;
        private int size = 1;
        private T key;

        private Node(T key) {
            this.key = key;
        }
    }

    /**
     * Returns the size of the Set.
     *
     * @return the size of the Set.
     */
    public int size() {
        if (root == null) {
            return 0;
        }
        else {
            return root.size;
        }
    }

    /**
     * Checks if key contains in tree or not.
     *
     * @param key to be searched
     * @return True if Set contains key, False otherwise.
     */
    public boolean contains(@NotNull T key) {
        Node n = root;
        while (n != null) {
            int cmp = n.key.compareTo(key);
            if (cmp == 0) {
                break;
            }

            if (cmp < 0) {
                n = n.right;
            }
            else {
                n = n.left;
            }
        }

        return n != null;
    }

    /**
     * Adds key in the Set.
     *
     * @param key to be added.
     * @return True if key was added, False otherwise.
     */
    public boolean add(@NotNull T key) {
        if (contains(key)) {
            return  false;
        }
        if (root == null) {
            root = new Node(key);
            return true;
        }

        Node n_new = new Node(key);
        Node n = root;
        while (true) {
            Node cur = null;
            if (n.key.compareTo(key) < 0) {
                cur = n.right;
            }
            else {
                cur = n.left;
            }
            n.size++;

            if (cur == null) {
                if (n.key.compareTo(key) < 0) {
                    n.right = n_new;
                }
                else {
                    n.left = n_new;
                }

                break;
            }

            n = cur;
        }

        return true;
    }
}
