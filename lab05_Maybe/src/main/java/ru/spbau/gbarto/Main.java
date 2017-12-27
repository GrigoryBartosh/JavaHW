package ru.spbau.gbarto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * An example of using Maybe.
 * Reads int values from file and writes squares into the other file.
 */
public class Main {
    private static final String FILE_IN = "src/main/resources/in.txt";
    private static final String FILE_OUT = "src/main/resources/out.txt";

    public static void main(String[] args) throws MaybeException {
        try (   BufferedReader in  = new BufferedReader(new FileReader(FILE_IN));
                PrintWriter    out = new PrintWriter(FILE_OUT) ) {
            for (String s = null; (s = in.readLine()) != null; ) {
                Maybe<Integer> mb_in = Maybe.just(Integer.parseInt(s));
                Maybe<Integer> mb_out = mb_in.map(integer -> integer * integer);
                out.println(mb_out.isPresent() ? mb_out.get() : null);
            }
        } catch (IOException e) {
            System.out.println("IO error:");
            e.printStackTrace();
        }
    }
}
