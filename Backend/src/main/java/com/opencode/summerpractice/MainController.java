package com.opencode.summerpractice;

import com.opencode.summerpractice.game_algorithm.Gameplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class MainController {
    @Autowired
    Gameplay gameplay;
//    @GetMapping("/")
//    public String main(ModelMap model){
//        return "main";
//    }

    @PostMapping("/")
    public Map<String, String> login(@ModelAttribute("username") String username){
        gameplay.setUserEntity(username);
        HashMap<String, String> map = new HashMap<>();
        map.put("answer", "user added");
        return map;
    }
}

