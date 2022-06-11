package com.opencode.summerpractice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
class PropertiesTest {
    @Value("${limitations}")
    private boolean limitations;

    public String getTurnsLimitation() {
        return turnsLimitation;
    }

    @Value("${turnsLimitation}")
    private String turnsLimitation;

    @Value("${timeLimitation}")
    private int timeLimitation;
}

