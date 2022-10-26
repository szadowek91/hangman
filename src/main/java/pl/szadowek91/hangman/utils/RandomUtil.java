package pl.szadowek91.hangman.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author PG
 */
public class RandomUtil {

    public static int selectWordNumber(int size) {
        return ThreadLocalRandom.current().nextInt(size);
    }
}
