package ru.spbau.gbarto;

public class LinkedList {
    private Node head;
    private Node tail;

    /**
     * Class for storing the keys and values.
     */
    static public class Pair {
        private final String key;
        private final String value;

        /**
         * Constructor by key and value.
         *
         * @param key
         * @param value
         */
        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Get a pair key.
         *
         * @return key
         */
        public String getKey() {
            return key;
        }

        /**
         * Get a pair value.
         *
         * @return value
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * A Node is an element of which the list consists.
     */
    private static class Node {
        private Node next;
        private Pair data;

        /**
         * Constructor by data.
         *
         * @param data
         */
        public Node(Pair data) {
            this.data = data;
        }

        /**
         * Get next Node in the list.
         *
         * @return next
         */
        public Node getNext() {
            return next;
        }

        /**
         * Set the value of the next to n.
         *
         * @param n next Node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Get the data stored in the Node.
         *
         * @return data
         */
        public Pair getData() {
            return data;
        }
    }

    /**
     * Get Head of the list.
     *
     * @return head
     */
    public Node getHead() {
        return head;
    }

    /**
     * Check if list contains Node with such key.
     *
     * @param key A String
     * @return boolean (contains or not)
     */
    public boolean contains(String key) {
        for (Node n = head; n != null; n = n.getNext()) {
            if (n.getData().getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the value by key.
     *
     * @param key A String
     * @return value which corresponds to such key
     */
    public String get(String key) {
        for (Node n = head; n != null; n = n.getNext()) {
            if (n.getData().getKey().equals(key)) {
                return n.getData().getValue();
            }
        }
        return null;
    }

    /**
     * Adds a new pair of key and value at the end of the list.
     *
     * @param key   A String
     * @param value A String
     */
    public void add(String key, String value) {
        Pair data = new Pair(key, value);
        if (head == null) {
            head = new Node(data);
            tail = head;
        }
        else {
            tail.next = new Node(data);
            tail = tail.next;
        }
    }

    /**
     * Removes a Node by key.
     *
     * @param key A String
     */
    public void remove(String key) {
        if (head == null) {
            return;
        }

        if (head.getData().getKey().equals(key)) {
            if (head == tail) {
                head = null;
                tail = null;
            }
            else {
                head = head.next;
            }
        }
        else
        {
            for (Node n = head; n.getNext() != null; n = n.getNext()) {
                if (n.getNext().getData().getKey().equals(key)) {
                    n.setNext(n.getNext().getNext());
                }
            }
        }
    }

    /**
     * Makes the list empty.
     */
    public void clear() {
        head = null;
        tail = null;
    }
}