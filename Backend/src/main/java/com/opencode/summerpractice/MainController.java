package com.opencode.summerpractice;

import com.opencode.summerpractice.game_algorithm.GameProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private GameProperties gameProperties;

    @GetMapping("/")
    public String main(){
        System.out.println(gameProperties.getTurnsLimitation());
        System.out.println(gameProperties.getTimeLimitation());
        return "emptyTemplate";
    }
}

