package ru.spbau.gbarto;

import java.io.IOException;

/**
 * Receives source, regex and destination
 */
public class Main {
    public static void main(String[] args) {
        Unzipper unzipper = new Unzipper();

        try {
            unzipper.unzip(args[0], args[1], args[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
