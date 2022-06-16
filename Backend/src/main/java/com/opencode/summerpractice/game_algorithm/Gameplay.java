package com.opencode.summerpractice.game_algorithm;

import com.opencode.summerpractice.daos.GameEntityDao;
import com.opencode.summerpractice.daos.TurnEntityDao;
import com.opencode.summerpractice.daos.UserEntityDao;
import com.opencode.summerpractice.entities.GameEntity;
import com.opencode.summerpractice.entities.TurnEntity;
import com.opencode.summerpractice.entities.UserEntity;
import com.opencode.summerpractice.responds.GameRespond;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Gameplay {
    @Autowired
    private UserEntityDao userEntityDao;
    @Autowired
    private GameEntityDao gameEntityDao;
    @Autowired
    private TurnEntityDao turnEntityDao;

    @Value("${turnsLimitation}")
    @Getter
    private int turnsLimitation;
    @Value("${timeLimitation}")
    @Getter
    private int timeLimitation;

    public Long setUserEntity(String username) {
        UserEntity user = new UserEntity();
        user.setName(username);
        userEntityDao.save(user);
        return user.getId();
    }


    public Gameplay() {
    }

    public Long pickNewHiddenNumber(Long userId) {
        HiddenNumber hiddenNumber = new HiddenNumber();
        hiddenNumber.pickNumber();

        GameEntity gameEntity = new GameEntity();

        UserEntity user = userEntityDao.findById(userId);
        gameEntity.setUser(user);
        gameEntity.setHiddenNumber(hiddenNumber.toString());

        gameEntity.setWin(false);
        gameEntity.setIsEnd(false);
        gameEntity.setTurnsNumber(0);
        gameEntity.setGameTime(System.currentTimeMillis());
        gameEntityDao.save(gameEntity);
        return gameEntity.getId();
    }

    public GuessingResult guessHiddenNumber(Long gameId, String userNumber) { //
        GameEntity gameEntity = gameEntityDao.findById(gameId);

        gameEntity.setTurnsNumber(gameEntity.getTurnsNumber() + 1);
        gameEntityDao.update(gameEntity);
        int[] userNumberIntArray = new int[4];
        for (int i = 0; i < 4; i++)
            userNumberIntArray[i] = userNumber.charAt(i) - '0';

        HiddenNumber hiddenNumber = new HiddenNumber();
        hiddenNumber.setNumber(gameEntity.getHiddenNumber());
        GuessingResult result = hiddenNumber.guess(userNumberIntArray);
        checkWinCondition(result, gameEntityDao.findById(gameId));
        TurnEntity turn = new TurnEntity();
        turn.setUserNumber(userNumber);
        turn.setTimePast(System.currentTimeMillis() - gameEntity.getGameTime());
        turn.setGame(gameEntityDao.findById(gameId));
        turnEntityDao.save(turn);

        return result;
    }

    private void checkWinCondition(GuessingResult result, GameEntity gameEntity) {
        if (result.getBulls() == 4) {
            gameEntity.setWin(true);
            gameEntity.setIsEnd(true);
            gameEntity.setGameTime((System.currentTimeMillis() - gameEntity.getGameTime()) / 1000);

            gameEntityDao.update(gameEntity);
        } else if (gameEntity.getTurnsNumber() >= turnsLimitation
                || (System.currentTimeMillis() - gameEntity.getGameTime()) / 1000 > timeLimitation) {
            gameEntity.setWin(false);
            gameEntity.setIsEnd(true);
            gameEntity.setGameTime((System.currentTimeMillis() - gameEntity.getGameTime()) / 1000);

            gameEntityDao.update(gameEntity);
        }
    }

    public ArrayList<GameRespond> printResults(Long userId) {
        ArrayList<GameRespond> arrayList = new ArrayList<>();
        GameRespond gameRespond;
        for (GameEntity game : userEntityDao.findGames(userId)) {
            gameRespond = new GameRespond();
            gameRespond.setGameTime(game.getGameTime());
            gameRespond.setTurnsNumber(game.getTurnsNumber());
            if (game.isWin())
                gameRespond.setWin("победа");
            else gameRespond.setWin("поражение");
            gameRespond.setHiddenNumber(game.getHiddenNumber());
            ArrayList<String> turns = new ArrayList<>();
            for (TurnEntity turn : gameEntityDao.findTurns(game.getId())) {
                turns.add(turn.toString());
            }
            gameRespond.setTurns(turns);
            arrayList.add(gameRespond);
        }
        return arrayList;
    }

    public boolean isEnd(Long gameId){
        GameEntity gameEntity = gameEntityDao.findById(gameId);
        return gameEntity.getIsEnd();
    }

    public boolean isWin(Long gameId){
        GameEntity gameEntity = gameEntityDao.findById(gameId);
        return gameEntity.isWin();
    }
}

