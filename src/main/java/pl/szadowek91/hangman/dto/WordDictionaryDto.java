package pl.szadowek91.hangman.dto;

import lombok.Getter;

import java.util.List;

/**
 * @author PG
 */
@Getter
public class WordDictionaryDto {

    private List<Meaning> meanings;

}
