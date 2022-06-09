package com.opencode.summerpractice;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;

public class Algorithm {


    public static void main(String[] args) throws IOException {


        HiddenNumber hiddenNumber = new HiddenNumber();
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String number; //читаем строку с клавиатуры
        hiddenNumber.pickNumber();
//        System.out.println(hiddenNumber);
        for (int i = 0 ; i < 20; i++){
            number = bufferedReader.readLine();
            System.out.println(hiddenNumber.guess(number));
        }
    }
}

