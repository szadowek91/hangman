package pl.szadowek91.hangman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.szadowek91.hangman.service.WordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class mainCtrl {

    private final WordService wordService;

    public mainCtrl(WordService wordService) {
        this.wordService = wordService;
    }


    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping()
    public String game(Model model, HttpSession session) throws ServletException, IOException {
        if (session.getAttribute("word") != null) {
            String randomWord = wordService.selectRandomWord();
            session.setAttribute("word", randomWord);
        }
        String word = (String) session.getAttribute("word");
        model.addAttribute("word", word); // at the end to remove (for review purposes)

        return "hangman";
    }
}
