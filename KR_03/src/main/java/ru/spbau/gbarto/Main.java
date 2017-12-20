package ru.spbau.gbarto;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        SmartList<Integer> l = new SmartList<>();
        System.out.println(l.size());
        l.add(5);
        System.out.println(l.size());
        System.out.println(Arrays.toString(l.toArray()));
        l.add(4);
        System.out.println(l.size());
        System.out.println(Arrays.toString(l.toArray()));
    }
}
