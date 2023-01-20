package com.ggoraj.memorygame.score;

import com.ggoraj.memorygame.game.GameEntity;
import com.ggoraj.memorygame.game.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Service
public class ScoreService {
    @Autowired
    IGameRepository gameRepository;
    @Autowired
    IScoreRepository scoreRepository;

    public int getGameScore(String gameId) {
        Optional<GameEntity> optGame = gameRepository.findById(gameId);
        if(optGame.isEmpty()) return -1;
        GameEntity dbGame = optGame.get();
        return dbGame.getScore().getScore();
    }

    public Long getSummedUserScore(Long userId){
        Optional<Collection<GameEntity>>  optGames = Optional.ofNullable(gameRepository.findAllGamesByUserId(userId));
        if(optGames.isEmpty()) return -1l;
        ArrayList<GameEntity> games = (ArrayList<GameEntity>) optGames.get();

        Long summedScores = 0l;
        for (GameEntity game: games
             ) {
            summedScores += game.getScore().getScore();
        }

        return summedScores;
    }

}
