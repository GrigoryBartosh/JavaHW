package ru.spbau.gbarto;

import java.util.ArrayList;

public class MyLinkedHashMap {
    private static final int INIT_CAPACITY = 2;
    private static final int BALANCE_K = 3;
    private static final int PRIME = 239;

    private LinkedList linkList;
    private ArrayList<ArrayList<Node>> table;
    private int size;
    private int capacity;

    public MyLinkedHashMap() {
        table = new ArrayList<ArrayList<Node>>(INIT_CAPACITY);
        size = 0;
        capacity = INIT_CAPACITY;
    }

    private int getHash(String s) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++)
        {
            hash = hash * PRIME + s.charAt(i);
            hash %= capacity;
        }
        return (int)hash;
    }

    private void rebuild() {
        capacity *= 2;
        ArrayList<ArrayList<Node>> oldTable = table;
        table = new ArrayList<ArrayList<Node>>(capacity);

        for (ArrayList<Node> list : oldTable) {
            if (list == null) {
                continue;
            }

            for (Node n : list) {
                Pair pair = n.getData();

                int hash = getHash(pair.getKey());
                if (table.get(hash) == null) {
                    table.set(hash, new ArrayList<Node>());
                }

                table.get(hash).add(n);
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean contains(String key) {
        int hash = getHash(key);
        ArrayList<Node> list = table.get(hash);
        if (list == null) {
            return false;
        }

        for (Node n : list) {
            if (n.getData().getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    public String get(String key) {
        int hash = getHash(key);
        ArrayList<Node> list = table.get(hash);
        if (list == null) {
            return null;
        }

        for (Node n : list) {
            if (n.getData().getKey().equals(key)) {
                return n.getData().getValue();
            }
        }

        return null;
    }

    public void remove(String key) {
        if (!contains(key)) return;

        int hash = getHash(key);
        ArrayList<Node> list = table.get(hash);

        for (Node n : list) {
            if (n.getData().getKey().equals(key)) {
                linkList.remove(n);
                list.remove(n);
                size--;
                return;
            }
        }
    }

    public void put(String key, String value) {
        remove(key);

        size++;
        Pair pair = new Pair(key, value);
        Node n = new Node(pair);
        linkList.add(n);
        int hash = getHash(key);
        if (table.get(hash) == null) {
            table.set(hash, new ArrayList<Node>());
        }
        table.get(hash).add(n);

        if (size * BALANCE_K > capacity) rebuild();
    }

    public void clear() {
        linkList.clear();
        table = new ArrayList<ArrayList<Node>>(capacity);
        size = 0;
    }
}
