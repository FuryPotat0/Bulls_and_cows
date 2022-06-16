package com.opencode.summerpractice.responds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GameRespond { // используется для отправки статистики клиенту
    private Long gameTime;
    private int turnsNumber;
    private String win;
    private String hiddenNumber;
    private ArrayList<String> turns;
}

