package pl.szadowek91.hangman.utils;

import pl.szadowek91.hangman.entity.WordEntity;

public class WordMapper {

    public static WordEntity wordMapper(String word) {
        return new WordEntity(word);
    }
}
