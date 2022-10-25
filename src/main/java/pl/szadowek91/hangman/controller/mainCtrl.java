package pl.szadowek91.hangman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.szadowek91.hangman.service.DictService;
import pl.szadowek91.hangman.service.WordService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class mainCtrl {

    private final WordService wordService;
    private final DictService dictService;

    public mainCtrl(WordService wordService, DictService dictService) {
        this.wordService = wordService;
        this.dictService = dictService;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping()
    public String game(Model model, HttpSession session) {
        if (session.getAttribute("word") == null) {
            String randomWord = wordService.selectRandomWord();
            session.setAttribute("word", randomWord);
            List<String> hintsFromAPI = dictService.getHints(randomWord);
            String collectedHints = String.join(" || \n", hintsFromAPI);
            session.setAttribute("hint", collectedHints);
        }
        String word = (String) session.getAttribute("word");
        String hintsFromAPI = (String) session.getAttribute("hint");
        model.addAttribute("hint", hintsFromAPI);
        model.addAttribute("word", word); // at the end to remove (for review purposes)

        return "hangman";
    }

}
