package com.opencode.summerpractice;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Getter
@Setter
public class HiddenNumber {
    private int[] number;

    public HiddenNumber() {
        number = new int[10];
        for (int i = 0; i < 10; i++)
            number[i] = 0;
    }

    public void pickNumber(){
        ArrayList<Integer> pickedDigits = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            pickedDigits.add(i);
        Collections.shuffle(pickedDigits);
        for (int i = 0; i < 4; i++){
            number[pickedDigits.get(i)] = i + 1;
        }
    }

    public String guess(String userNumber){
        int bulls = 0;
        int cows = 0;
        int digit;
        for (int i = 0; i < 4; i++){
            digit = userNumber.charAt(i) - '0';
            if (number[digit] == i + 1)
                bulls++;
            else if (number[digit] != 0)
                cows++;
        }
        return "быки " + bulls + ", коровы " + cows;
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

