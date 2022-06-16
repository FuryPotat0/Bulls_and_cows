package com.opencode.summerpractice.controllers;

import com.opencode.summerpractice.responds.GameAndNumber;
import com.opencode.summerpractice.game_algorithm.Gameplay;
import com.opencode.summerpractice.game_algorithm.GuessingResult;
import com.opencode.summerpractice.responds.GameRespond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    @Autowired
    private Gameplay gameplay;

    @PostMapping("/game-page/start")
    public Map<String, String> startGame(@RequestBody String userId) {
        Long gameId = gameplay.pickNewHiddenNumber(Long.parseLong(userId));
        HashMap<String, String> map = new HashMap<>();
        map.put("gameId", gameId.toString());
        LOGGER.info("User started the game");
        return map;
    }

    @PostMapping("/game-page/guess")
    public Map<String, String> guessNumber(@RequestBody GameAndNumber number) {
        HashMap<String, String> map = new HashMap<>();
        if (!gameplay.isEnd(Long.parseLong(number.getGameId()))){
            System.out.println("number: " + number);
            GuessingResult res = gameplay.guessHiddenNumber(Long.parseLong(number.getGameId()), number.getNumber());
            map.put("result", "быков: " + res.getBulls() + " коров: " + res.getCows());
            map.put("isEnd", "false");
            map.put("isWon", "false");
            LOGGER.info("User guess number {}", number.getNumber());
        } else{
            map.put("isEnd", "true");
            if (gameplay.isWin(Long.parseLong(number.getGameId()))){
                LOGGER.info("User win the game");
                map.put("isWon", "true");
                map.put("result", "Игра окончена, вы победили");
            }
            else{
                LOGGER.info("User lose the game");
                map.put("isWon", "false");
                map.put("result", "Игра окончена, вы проиграли");
            }
        }
        if (gameplay.isEnd(Long.parseLong(number.getGameId()))){
            map.put("isEnd", "true");
            if (gameplay.isWin(Long.parseLong(number.getGameId()))){
                LOGGER.info("User win the game");
                map.put("isWon", "true");
                map.put("result", "Игра окончена, вы победили");
            }
            else{
                LOGGER.info("User lose the game");
                map.put("isWon", "false");
                map.put("result", "Игра окончена, вы проиграли");
            }
        }
        return map;
    }

    @PostMapping("/game-page/statistic")
    public ArrayList<GameRespond> getStatistic(@RequestBody String userId){
        LOGGER.info("Send user statistic");
        return gameplay.printResults(Long.parseLong(userId));
    }
}

