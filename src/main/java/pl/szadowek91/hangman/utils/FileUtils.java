package pl.szadowek91.hangman.utils;

import pl.szadowek91.hangman.entity.WordEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author PG
 */
public class FileUtils {

    public static List<WordEntity> readTxtBuffRead(String file) {
        List<WordEntity> wordList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(WordMapper.wordMapper(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    public static List<String> readTxt(String file) {
        try (Stream<String> stream = Files.lines(Paths.get(new File(file).getAbsolutePath()))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static String readAsText(File file) {
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return stream.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void saveFile(List<String> arr) {

        try {
            try (FileWriter fileWriter = new FileWriter("savedFile.txt")) {
                for (String str : arr) fileWriter.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
