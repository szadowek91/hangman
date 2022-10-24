package pl.szadowek91.hangman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.szadowek91.hangman.service.WordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping() // todo by using httpsession
    public String game(Model model, String input, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String randomWord = wordService.selectRandomWord();
        model.addAttribute("word", randomWord);
        session.setAttribute("word", randomWord);
        response.sendRedirect("hangman");
        return "hangman";}
}
