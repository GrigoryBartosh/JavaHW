package ru.spbau.gbarto;

/**
 * Implemented on a single-linked list
 */
public class HashTable {
    private static final int INIT_CAPACITY = 2;
    private static final int BALANCE_K = 3;
    private static final int PRIME = 239;

    private LinkedList[] table;
    private int size;
    private int capacity;

    /**
     * Initialization of the HashTable.
     */
    public HashTable() {
        table = new LinkedList[INIT_CAPACITY];
        size = 0;
        capacity = INIT_CAPACITY;
    }

    /**
     * Gets hash by string.
     *
     * @param s A String
     * @return Integer hash
     */
    private int getHash(String s) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++)
        {
            hash = hash * PRIME + s.charAt(i);
            hash %= capacity;
        }
        return (int)hash;
    }

    /**
     * Reconfigures the table, doubling the number of cells.
     */
    private void rebuild() {
        capacity *= 2;
        LinkedList[] oldTable = table;
        table = new LinkedList[capacity];

        for (LinkedList list : oldTable) {
            if (list == null) {
                continue;
            }

            for (LinkedList.Node n = list.getHead(); n != null; n = n.getNext()) {
                LinkedList.Pair pair = n.getData();

                int hash = getHash(pair.getKey());
                if (table[hash] == null) {
                    table[hash] = new LinkedList();
                }

                table[hash].add(pair.getKey(), pair.getValue());
            }
        }
    }

    /**
     * Returns size.
     *
     * @return Count of pairs in HashTable
     */
    public int size() {
        return size;
    }

    /**
     * Check if HashTable contains Pair with such key.
     *
     * @param key A String
     * @return boolean (contains or not)
     */
    public boolean contains(String key) {
        int hash = getHash(key);
        return table[hash] != null && table[hash].contains(key);
    }

    /**
     * Gets the value by key.
     *
     * @param key A String
     * @return value which corresponds to such key
     */
    public String get(String key) {
        int hash = getHash(key);
        if (table[hash] == null) {
            return null;
        }

        return table[hash].get(key);
    }

    /**
     * Adds a new pair of key and value at the HashTable.
     *
     * @param key   A String
     * @param value A String
     */
    public void put(String key, String value) {
        int hash = getHash(key);
        if (table[hash] == null) {
            table[hash] = new LinkedList();
        }

        LinkedList list = table[hash];
        if (list.contains(key)) {
            list.remove(key);
        }
        else {
            size++;
        }
        list.add(key, value);

        if (size * BALANCE_K > capacity) rebuild();
    }

    /**
     * Removes a Pair by key.
     *
     * @param key A String
     */
    public void remove(String key) {
        int hash = getHash(key);
        if (table[hash] == null) {
            return;
        }

        LinkedList list = table[hash];
        if (list.contains(key)) {
            size--;
        }
        list.remove(key);
    }

    /**
     * Makes HashTable empty.
     */
    public void clear() {
        for (LinkedList list : table) {
            if (list != null)
            {
                list.clear();
            }
        }
        size = 0;
    }
}