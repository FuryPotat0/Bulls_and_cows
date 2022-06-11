package com.opencode.summerpractice.game_algorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HiddenNumberBehavior { //TODO сделать проверку на количество ходов и время игры
    private HiddenNumber hiddenNumber;
    private int turns;
    private boolean isWon;
    private boolean isEnd;
    private long startTime; // проверка времени игры

    @Value("${turnsLimitation}")
    private int turnsLimitation;
    @Value("${timeLimitation}")
    private int timeLimitation;

    public HiddenNumberBehavior() {
        hiddenNumber = new HiddenNumber();
    }

    public void pickNewHiddenNumber(){
        startTime = System.currentTimeMillis();
        hiddenNumber.pickNumber();
        turns = 0;
        isWon = false;
        isEnd = false;
    }

    public GuessingResult guessHiddenNumber(String userNumber){
        turns++;
        int[] userNumberIntArray = new int[4];
        for (int i = 0; i < 4; i++)
            userNumberIntArray[i] = userNumber.charAt(i) - '0';
        GuessingResult result = hiddenNumber.guess(userNumberIntArray);
        checkWinCondition(result);
        return result;
    }

    private void checkWinCondition(GuessingResult result){
        //TODO сделать проверку на на победу и конец игры
    }
}

