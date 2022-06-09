package com.opencode.summerpractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private PropertiesTest propertiesTest;

    @GetMapping("/")
    public String main(){
        System.out.println(propertiesTest.isLimitations());
        System.out.println(propertiesTest.getTurnsLimitation());
        System.out.println(propertiesTest.getTimeLimitation());
        return "emptyTemplate";
    }
}

