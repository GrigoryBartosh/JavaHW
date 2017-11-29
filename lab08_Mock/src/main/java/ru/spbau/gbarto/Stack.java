package ru.spbau.gbarto;

import java.util.NoSuchElementException;

/**
 * The stack is implemented by the list.
 * @param <T> type of elements to store in Stack
 */
public class Stack<T> {
    public Node root = null;

    private class Node {
        private T value;
        private Node next;

        private Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Checks if stack is empty.
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Puts element in the stack.
     * @param element new element
     */
    public void push(T element) {
        root = new Node(element, root);
    }

    /**
     * Returns the last element in the stack and deletes it.
     * @return last element in the stack
     */
    public T pop() {
        if (root == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        T res = root.value;
        root = root.next;
        return res;
    }

    /**
     * Returns the last element in the stack.
     * @return the last element in the stac
     */
    public T getTop() {
        if (root == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        return root.value;
    }

    /**
     * Deletes all the elements from the stack.
     */
    public void clear() {
        root = null;
    }
}
