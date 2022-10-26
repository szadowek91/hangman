package pl.szadowek91.hangman.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.szadowek91.hangman.entity.WordEntity;
import pl.szadowek91.hangman.utils.FileUtils;
import pl.szadowek91.hangman.utils.RandomUtil;
import pl.szadowek91.hangman.utils.WordMapper;

import java.util.*;

/**
 * @author PG
 */
@Service
public class WordService {
    private final String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("words.txt")).getPath();

    @SneakyThrows
    public String selectRandomWord() {
        List<WordEntity> list = FileUtils.readTxt(path).stream()
                .map(WordMapper::wordMapper)
                .toList();
        return list.get(RandomUtil.selectWordNumber(list.size())).getWord();
    }

    public String initShowActualWord(String word) {
        LinkedList<String> replacedWord = new LinkedList<>();
        List<String> strings = Arrays.stream(word.split("")).toList();
        for (String letter : strings) {
            String replacedLetter = letter.replace(letter.charAt(0), '_');
            replacedWord.add(replacedLetter);
        }
        return String.join(" ", replacedWord);
    }

    public String checkLetterInWord(String inputLetter, String word, String actualWord) {
        LinkedList<String> replacedWord = new LinkedList<>();
            List<String> strings = Arrays.stream(word.split("")).toList();
            for (String letter : strings) {
                String replacedLetter = letter.replace(letter.charAt(0), '_');
                if (letter.matches(inputLetter.toUpperCase())) {
                    replacedLetter = letter;
                } else if (actualWord.contains(letter)) {
                    replacedLetter = letter;
                }
                replacedWord.add(replacedLetter);
            }
        return String.join(" ", replacedWord);
    }

    public String usedLettersOk(String inputLetter, String word, String usedLettersCorrect) {
        String lettersOK = usedLettersCorrect;
        if (word.contains(inputLetter.toUpperCase()) && !usedLettersCorrect.contains(inputLetter.toUpperCase())) {
            lettersOK = usedLettersCorrect.concat(" ").concat(inputLetter.toUpperCase());
            return lettersOK;
        }
        return lettersOK;
    }

    public String usedLettersNotOk(String inputLetter, String word, String usedLettersDoesNotMatch) {
        String lettersNotOk = usedLettersDoesNotMatch;
        if (!word.contains(inputLetter.toUpperCase()) && !usedLettersDoesNotMatch.contains(inputLetter.toUpperCase())) {
            lettersNotOk = usedLettersDoesNotMatch.concat(" ").concat(inputLetter.toUpperCase());
            return lettersNotOk;
        }
        return lettersNotOk;
    }
}
