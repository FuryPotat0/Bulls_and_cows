package com.opencode.summerpractice.game_algorithm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuessingResult {
    private int bulls;
    private int cows;

    public GuessingResult(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
    }
}

