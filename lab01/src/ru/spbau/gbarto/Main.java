package ru.spbau.gbarto;

public class Main {

    public static void main(String[] args) {
        HashTable map = new HashTable();

        map.put("a", "b");
        map.put("c", "d");
        System.out.println("put (a, b)");
        System.out.println("put (c, d)");
        System.out.println("size = " + map.size());
        System.out.println("contains(\"a\") = " + map.contains("a"));
        System.out.println("get(\"a\") = " + map.get("a"));
        map.remove("a");
        System.out.println("remove(\"a\")");
        System.out.println("size = " + map.size());
        System.out.println("contains(\"a\") = " + map.contains("a"));
        System.out.println("get(\"a\") = " + map.get("a"));
        System.out.println("contains(\"c\") = " + map.contains("c"));
        System.out.println("get(\"c\") = " + map.get("c"));
        map.clear();
        System.out.println("size = " + map.size());
    }
}
