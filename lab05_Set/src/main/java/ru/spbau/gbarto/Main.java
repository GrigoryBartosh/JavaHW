package ru.spbau.gbarto;

/**
 * Example of using Set.
 */
public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new Set<>();
        System.out.println("add 1: " + String.valueOf(set.add(1)));
        System.out.println("add 2: " + String.valueOf(set.add(2)));
        System.out.println("add 3: " + String.valueOf(set.add(3)));
        System.out.println("add 1: " + String.valueOf(set.add(1)));

        System.out.println("contains 1: " + String.valueOf(set.contains(1)));
        System.out.println("contains 2: " + String.valueOf(set.contains(2)));
        System.out.println("contains 5: " + String.valueOf(set.contains(5)));

        System.out.println("size: " + String.valueOf(set.size()));
    }
}
