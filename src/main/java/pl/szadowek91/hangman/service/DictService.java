package pl.szadowek91.hangman.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.szadowek91.hangman.dto.WordDefinition;
import pl.szadowek91.hangman.dto.WordDictionaryDto;
import pl.szadowek91.hangman.utils.ConstantPhrase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author PG
 */

@Service
public class DictService {

    @SneakyThrows
    public List<String> getHints(String word) {
        Optional<WordDictionaryDto> wordDict = getWordDict(word);
        List<String> stringList = new ArrayList<String>();
        if (wordDict.isEmpty()) {
            return stringList;
        }
        stringList = wordDict.get().getMeanings()
                .stream()
                .map(m -> m.getDefinitions().stream()
                        .map(WordDefinition::getDefinition)
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .toList();
        return stringList;
    }

    private Optional<WordDictionaryDto> getWordDict(String word) {
        try {
            URL url = new URL(ConstantPhrase.DICTIONARY_API_URL + word);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            String s = readInputStream(reader);
            Type type = new TypeToken<List<WordDictionaryDto>>() {
            }.getType();
            List<WordDictionaryDto> wordDictionaryDtos = new Gson().fromJson(s, type);
            Optional<WordDictionaryDto> wordDictionaryDto = Optional.of(wordDictionaryDtos.get(0));
            return wordDictionaryDto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private String readInputStream(InputStreamReader reader) {
        try (BufferedReader in = new BufferedReader(reader)) {
            return in.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
