package com.opencode.summerpractice.game_algorithm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Getter
@Setter
@Service
public class HiddenNumber {
    private int[] number;

    public HiddenNumber() {
        number = new int[10];
        for (int i = 0; i < 10; i++)
            number[i] = 0;
    }

    private void clearNumber(){
        number = new int[10];
        for (int i = 0; i < 10; i++)
            number[i] = 0;
    }

    public void pickNumber(){
        clearNumber();
        ArrayList<Integer> pickedDigits = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            pickedDigits.add(i);
        Collections.shuffle(pickedDigits);
        for (int i = 0; i < 4; i++)
            number[pickedDigits.get(i)] = i + 1;
    }

    public GuessingResult guess(int[] userNumber){
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < 4; i++){
            if (number[userNumber[i]] == i + 1)
                bulls++;
            else if (number[userNumber[i]] != 0)
                cows++;
        }
        return new GuessingResult(bulls, cows);
    }

    @Override
    public String toString(){
        int[] num = new int[4];
        for (int i = 0; i < 10; i++){
            if (number[i] != 0){
                num[number[i] - 1] = i;
            }
        }
        return Arrays.toString(num);
    }
}

