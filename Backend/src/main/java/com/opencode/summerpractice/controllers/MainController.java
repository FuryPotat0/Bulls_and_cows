package com.opencode.summerpractice.controllers;

import com.opencode.summerpractice.game_process.Gameplay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    @Autowired
    Gameplay gameplay;
    @PostMapping("/")
    public Map<String, String> login(@RequestBody String username){
        HashMap<String, String> map = new HashMap<>();
        map.put("turnsLimitation", String.valueOf(gameplay.getTurnsLimitation()));
        map.put("timeLimitation", String.valueOf(gameplay.getTimeLimitation()));
        map.put("userId", gameplay.setUserEntity(username).toString());
        LOGGER.info("user {} login", username);
        return map;
    }
}

