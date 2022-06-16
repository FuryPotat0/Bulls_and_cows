package com.opencode.summerpractice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestController {
    @GetMapping("/test")
    public TestUser getTest(){
        TestUser user = new TestUser();
        user.setStr("FuryPotat0");
        return user;
    }
}

