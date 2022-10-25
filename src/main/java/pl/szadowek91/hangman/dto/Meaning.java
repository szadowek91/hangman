package pl.szadowek91.hangman.dto;

import lombok.Getter;

import java.util.List;

/**
 * @author PG
 */
@Getter
public class Meaning {

    private List<WordDefinition> definitions;
}
