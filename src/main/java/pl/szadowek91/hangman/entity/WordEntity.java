package pl.szadowek91.hangman.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * @author PG
 */
@NoArgsConstructor
@Entity
public class WordEntity {

    @Id
    private int id;
    private String word;

    public WordEntity(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

}
