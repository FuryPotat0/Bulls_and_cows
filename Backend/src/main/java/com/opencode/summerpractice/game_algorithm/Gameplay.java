package com.opencode.summerpractice.game_algorithm;

import com.opencode.summerpractice.daos.GameEntityDao;
import com.opencode.summerpractice.daos.TurnEntityDao;
import com.opencode.summerpractice.daos.UserEntityDao;
import com.opencode.summerpractice.entities.GameEntity;
import com.opencode.summerpractice.entities.TurnEntity;
import com.opencode.summerpractice.entities.UserEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class Gameplay { //TODO сделать проверку на количество ходов и время игры
    @Getter
    private HiddenNumber hiddenNumber;
    @Getter
    private int turns;
    @Getter
    private boolean isWon;
    @Getter
    private boolean isEnd;
    private long startTime; // проверка времени игры

    @Autowired
    private GameProperties properties;
    @Autowired
    private UserEntityDao userEntityDao;
    @Autowired
    private GameEntityDao gameEntityDao;
    @Autowired
    private TurnEntityDao turnEntityDao;
    @Getter
    private UserEntity userEntity;
    private GameEntity gameEntity;

    public void setUserEntity(String username) {
        UserEntity user = new UserEntity();
        user.setName(username);
        this.userEntity = user;
        userEntityDao.save(userEntity);
    }

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

        gameEntity = new GameEntity();
        gameEntity.setUser(userEntity);
        gameEntity.setHiddenNumber(hiddenNumber.toString());

        gameEntity.setWin(false);
        gameEntity.setTurnsNumber(0);
        gameEntity.setGameTime(0L);
        gameEntityDao.save(gameEntity);
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
        System.out.println("GameEntity id = " + gameEntity.getId());
        if (!isEnd){
            turns++;
            int[] userNumberIntArray = new int[4];
            for (int i = 0; i < 4; i++)
                userNumberIntArray[i] = userNumber.charAt(i) - '0';
            GuessingResult result = hiddenNumber.guess(userNumberIntArray);
            checkWinCondition(result);
            TurnEntity turn = new TurnEntity();
            turn.setUserNumber(userNumber);
            turn.setTimePast(System.currentTimeMillis() - startTime);
            turn.setGame(gameEntity);

            turnEntityDao.save(turn);
            return result;
        }
        return null;
    }

    private void checkWinCondition(GuessingResult result){
        if (result.getBulls() == 4) {
            isEnd = true;
            isWon = true;
            gameEntity.setWin(true);
            System.out.println("id before:" + gameEntity.getId());
            gameEntityDao.update(gameEntity);
            System.out.println("id after:" + gameEntity.getId());
        }else if (turns >= turnsLimitation || (System.currentTimeMillis() - startTime) / 100 > timeLimitation){
            isEnd = true;
            gameEntity.setWin(false);
            gameEntity.setTurnsNumber(turns);
            gameEntity.setGameTime((System.currentTimeMillis() - startTime) / 100);
            System.out.println("id before:" + gameEntity.getId());
            gameEntityDao.update(gameEntity);
            System.out.println("id after:" + gameEntity.getId());
        }
    }

    public void printResults(){
        for(GameEntity game: userEntityDao.findGames(userEntity.getId())){
            System.out.println(game);
            for(TurnEntity turn: gameEntityDao.findTurns(game.getId())){
                System.out.println(turn);
            }
        }
    }


}

