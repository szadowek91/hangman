package pl.szadowek91.hangman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.szadowek91.hangman.service.WordSelector;

@Controller
public class mainCtrl {

    private final WordSelector wordService;

    public mainCtrl(WordSelector wordService) {
        this.wordService = wordService;
    }


    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
