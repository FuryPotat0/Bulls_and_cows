package com.opencode.summerpractice.game_process;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Getter
@Setter
@Service
public class HiddenNumber {
    private int[] number; // загаданное число - массив из 10 элнментов, индекс это цифра в загаданном числе,
    // а заначение - индекс этой цифры в числе, начиная с единицы

    public HiddenNumber() {
        number = new int[10];
    }

    private void clearNumber(){
        number = new int[10];
    }

    public void setNumber(String userNumber) { // устанавливает заданное число
        clearNumber();
        int[] userNumberIntArray = new int[4];
        for (int i = 0; i < 4; i++)
            userNumberIntArray[i] = userNumber.charAt(i) - '0';
        for (int i = 0; i < 4; i++){
            this.number[userNumberIntArray[i]] = i + 1;
        }
    }

    public void pickNumber(){ //
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
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 4; i++)
            str.append(num[i]);
        return str.toString();
    }
}

