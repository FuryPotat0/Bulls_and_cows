package com.opencode.summerpractice;

import com.opencode.summerpractice.game_algorithm.GameProperties;
import com.opencode.summerpractice.game_algorithm.Gameplay;
import com.opencode.summerpractice.game_algorithm.GuessingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {
    @Autowired
    private Gameplay gameplay;
    @Autowired
    private GameProperties properties;

    private boolean isGameStarted = false;

    @GetMapping("/game-page")
    public String getGamePage(ModelMap model,
                              @RequestParam(value = "previousNumber", required = false) String previousNumber) {
        gameplay.testDatabase();
        if (!isGameStarted){
            model.addAttribute("gameMessage", "press start button");
            fillFields(model);
        }
        else {
            fillFields(model);
            model.addAttribute("gameMessage", "");
            model.addAttribute("previousNumber", previousNumber);
        }
        return "game-page";
    }

    @GetMapping("/game-page/start")
    public ModelAndView startGame(ModelMap model) {
        isGameStarted = true;
        model.addAttribute("gameMessage", "game started");
        gameplay.pickNewHiddenNumber();
        System.out.println(gameplay.toString());
        return new ModelAndView("redirect:/game-page", model);
    }

    @PostMapping("/game-page/guess")
    public String guessNumber(@ModelAttribute("number") String number, ModelMap model) {
        GuessingResult res = gameplay.guessHiddenNumber(number);
        model.addAttribute("result", "bulls: " + res.getBulls() + " cows: " + res.getCows());
        model.addAttribute("previousNumber", number);
        fillFields(model);
        return "game-page";
    }

    public void fillFields(ModelMap model) {
        model.addAttribute("curTurn", gameplay.getTurns());
        model.addAttribute("turnsLeft", gameplay.getTurnsLeft());
    }
}

