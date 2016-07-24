package utils;

import java.util.Random;

public class RandomData {

    private static final char[] SYMBOLS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();
    private static final Random RND = new Random();

    private RandomData() {
    }

    public static String getString(int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1: " + length);

        char[] chars = new char[length];
        for (int i = 0; i < length; ++i)
            chars[i] = SYMBOLS[RND.nextInt(SYMBOLS.length)];

        return new String(chars);
    }
}
