package com.chessgame.util;

/**
 * @author Bittere_Gift
 */
public class Util {

    private Util() {}

    public static void printLine() {
        System.out.println(getLine());
    }

    public static String getLine() {
        return "--------------------------------------------------" +
                "--------------------------------------------------";
    }
}
