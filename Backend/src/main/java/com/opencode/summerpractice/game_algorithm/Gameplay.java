package com.opencode.summerpractice.game_algorithm;

import com.opencode.summerpractice.daos.GameEntityDao;
import com.opencode.summerpractice.daos.UserEntityDao;
import com.opencode.summerpractice.entities.GameEntity;
import com.opencode.summerpractice.entities.UserEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class Gameplay { //TODO сделать проверку на количество ходов и время игры
    private HiddenNumber hiddenNumber;
    @Getter
    private int turns;
    private boolean isWon;
    private boolean isEnd;
    private long startTime; // проверка времени игры

    @Autowired
    private GameProperties properties;
    @Autowired
    private UserEntityDao userEntityDao;
    @Autowired
    private GameEntityDao gameEntityDao;

    @Value("${turnsLimitation}")
    private int turnsLimitation;
    @Value("${timeLimitation}")
    private int timeLimitation;

    public Gameplay() {
        hiddenNumber = new HiddenNumber();
        turns = 0;
        isWon = false;
        isEnd = false;
        startTime = 0;
    }

    public int getTurnsLeft(){
        return turnsLimitation - turns;
    }

    public void pickNewHiddenNumber(){
        startTime = System.currentTimeMillis();
        hiddenNumber.pickNumber();
        turns = 0;
        isWon = false;
        isEnd = false;
    }

    public void testDatabase(){
        UserEntity user = new UserEntity();
        user.setName("FuryPotat0");
        userEntityDao.save(user);

        GameEntity game1 = new GameEntity();
        game1.setHiddenNumber("1456");
        game1.setGameTime(100L);
        game1.setWin(true);
        game1.setTurnsNumber(3);
        game1.setUser(user);


//        GameEntity game2 = new GameEntity();
//        game2.setHiddenNumber("1026");
//        game2.setGameTime(400L);
//        game2.setWin(false);
//        game2.setTurnsNumber(30);


//        user.getGames().add(game1);
//        user.getGames().add(game2);
//        user.setGames1();

        gameEntityDao.save(game1);
//        gameEntityDao.save(game2);
//        userEntityDao.update(user);

        userEntityDao.findGames(user.getId());
    }

    public GuessingResult guessHiddenNumber(String userNumber){
        turns++;
        int[] userNumberIntArray = new int[4];
        for (int i = 0; i < 4; i++)
            userNumberIntArray[i] = userNumber.charAt(i) - '0';
        GuessingResult result = hiddenNumber.guess(userNumberIntArray);
        checkWinCondition(result);
        return result;
    }

    private void checkWinCondition(GuessingResult result){
        if (turns > turnsLimitation || (System.currentTimeMillis() - startTime) / 100 > timeLimitation){
            isEnd = true;
        } else if (result.getBulls() == 4) {
            isEnd = true;
            isWon = true;
        }
    }
}

