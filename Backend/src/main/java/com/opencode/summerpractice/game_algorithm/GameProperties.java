package com.opencode.summerpractice.game_algorithm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class GameProperties {
    @Value("${turnsLimitation}")
    private String turnsLimitation;

    @Value("${timeLimitation}")
    private int timeLimitation;
}

