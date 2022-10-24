package pl.szadowek91.hangman.service;

import org.springframework.stereotype.Service;
import pl.szadowek91.hangman.entity.WordEntity;
import pl.szadowek91.hangman.utils.FileUtils;
import pl.szadowek91.hangman.utils.RandomUtil;
import pl.szadowek91.hangman.utils.WordMapper;

import java.util.List;

@Service
public class WordSelector {

    private final String path = "D:\\patrgra\\hangman\\src\\main\\resources\\words.txt"; // todo pobieranie z resource sciezki

    public String selectRandomWord() {
        List<WordEntity> list = FileUtils.readTxt(path).stream()
                .map(WordMapper::wordMapper)
                .toList();
        return list.get(RandomUtil.selectWordNumber(list.size())).getWord();
    }
}
