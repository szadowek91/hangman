package pl.szadowek91.hangman.service;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.szadowek91.hangman.dto.WordDictionaryDto;
import pl.szadowek91.hangman.utils.ConstantPhrase;

import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author PG
 */

@Service
public class DictService {

    @SneakyThrows
    public String getHints(String word) {
        URL url = new URL(ConstantPhrase.DICTIONARY_API_URL + word);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        WordDictionaryDto dto = new Gson().fromJson(reader, WordDictionaryDto.class);

        return null;
    }
}
