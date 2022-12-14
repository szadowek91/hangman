package pl.szadowek91.hangman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szadowek91.hangman.service.DictService;
import pl.szadowek91.hangman.service.WordService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author PG
 */
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
            String actualWord = wordService.initShowActualWord(randomWord);
            List<String> hintsFromAPI = dictService.getHints(randomWord);
            String collectedHints = String.join(" || \n", hintsFromAPI);

            session.setAttribute("hint", collectedHints);
            session.setAttribute("actualWord", actualWord);
            session.setAttribute("usedLettersCorrect", "");
            session.setAttribute("usedLettersDoesNotMatch", "");
            session.setAttribute("word", randomWord);
        }
        String word = (String) session.getAttribute("word");
        String actualWord = (String) session.getAttribute("actualWord");
        String hintsFromAPI = (String) session.getAttribute("hint");
        String usedLettersOK = (String) session.getAttribute("usedLettersCorrect");
        String usedLettersNotOK = (String) session.getAttribute("usedLettersDoesNotMatch");

        model.addAttribute("actualWord", actualWord);
        model.addAttribute("hint", hintsFromAPI);
        model.addAttribute("usedLettersCorrect", usedLettersOK);
        model.addAttribute("usedLettersDoesNotMatch", usedLettersNotOK);
        model.addAttribute("word", word); // at the end to remove (for review purposes)

        return "hangman";
    }

    @PostMapping("/enteredLetter")
    public String enteredLetter(@RequestParam("inputLetter") String inputLetter, HttpSession session){
        String word = (String) session.getAttribute("word");
        String actualWord = (String) session.getAttribute("actualWord");
        String usedLettersCorrect = (String) session.getAttribute("usedLettersCorrect");
        String usedLettersDoesNotMatch = (String) session.getAttribute("usedLettersDoesNotMatch");

        String usedLettersOK = wordService.usedLettersOk(inputLetter, word, usedLettersCorrect);
        String usedLettersNotOK = wordService.usedLettersNotOk(inputLetter, word, usedLettersDoesNotMatch);
        String showLetterInWord = wordService.checkLetterInWord(inputLetter, word, actualWord);

        session.setAttribute("usedLettersCorrect", usedLettersOK);
        session.setAttribute("usedLettersDoesNotMatch", usedLettersNotOK);
        session.setAttribute("actualWord", showLetterInWord);
        return "redirect:/";
    }

    @PostMapping("/newGame")
    public String newGame(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
