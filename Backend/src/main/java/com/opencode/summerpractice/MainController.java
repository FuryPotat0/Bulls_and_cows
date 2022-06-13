package com.opencode.summerpractice;

import com.opencode.summerpractice.game_algorithm.Gameplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    Gameplay gameplay;
    @GetMapping("/")
    public String main(ModelMap model){
        return "main";
    }

    @PostMapping("/")
    public String login(@ModelAttribute("username") String username, ModelMap model){
        gameplay.setUserEntity(username);
        return "redirect:/game-page";
    }
}

