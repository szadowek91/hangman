package pl.szadowek91.hangman.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.szadowek91.hangman.entity.WordEntity;
import pl.szadowek91.hangman.utils.FileUtils;
import pl.szadowek91.hangman.utils.RandomUtil;
import pl.szadowek91.hangman.utils.WordMapper;

import java.util.List;
import java.util.Objects;

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
}
