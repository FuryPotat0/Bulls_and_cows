package com.opencode.summerpractice;

import com.opencode.summerpractice.game_algorithm.HiddenNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {
    @Autowired
    private HiddenNumber hiddenNumber;
    @Autowired
    private PropertiesTest properties;

    private boolean isGameStarted = false;

    @GetMapping("/game-page")
    public String getGamePage(Model model,
                              @RequestParam(value = "previousNumber", required = false) String previousNumber,
                              @RequestParam(value = "result", required = false) String result){
        if (!isGameStarted)
            model.addAttribute("result", "press start button");
        else {
            model.addAttribute("curTurn", hiddenNumber.getTurns());
            model.addAttribute("turnsLeft", Integer.parseInt(properties.getTurnsLimitation()) - hiddenNumber.getTurns());
            model.addAttribute("result", result);
            model.addAttribute("previousNumber", previousNumber);
        }
        return "game-page";
    }

    @GetMapping("/game-page/start")
    public ModelAndView startGame(ModelMap model){
        isGameStarted = true;
        model.addAttribute("result", "game started");
        hiddenNumber.pickNumber();
        System.out.println(hiddenNumber.toString());
        return new ModelAndView("redirect:/game-page", model);
    }

    @PostMapping("/game-page/guess")
    public ModelAndView guessNumber(@ModelAttribute("number") String number, ModelMap model){
        model.addAttribute("result", hiddenNumber.guess(number));
        model.addAttribute("previousNumber", number);
        return new ModelAndView("redirect:/game-page", model);
    }
}

