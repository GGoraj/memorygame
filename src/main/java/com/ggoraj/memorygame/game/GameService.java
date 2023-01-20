package com.ggoraj.memorygame.game;

import com.ggoraj.memorygame.game.engine.Cell;
import com.ggoraj.memorygame.game.engine.Game;
import com.ggoraj.memorygame.game.msg.MatchResponse;
import com.ggoraj.memorygame.picture.PictureRepository;
import com.ggoraj.memorygame.score.IScoreRepository;
import com.ggoraj.memorygame.score.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service

public class GameService {

    private Map<String, Game> games;
    private MatchMaker matchMaker;

    private PictureRepository pictureRepository;
    private IScoreRepository scoreRepository;
    private IGameRepository gameRepository;


    // constructor
    @Autowired
    public GameService(IScoreRepository scoreRepository,
                       MatchMaker matchMaker,
                       PictureRepository pictureRepository,
                       IGameRepository gameRepository) {
        games = new HashMap();
        this.pictureRepository = pictureRepository;
        this.matchMaker = matchMaker;
        this.scoreRepository = scoreRepository;
        this.gameRepository = gameRepository;
    }


    public String startGame(Long userId, int gameSize) {

        GameEntity game = new GameEntity(userId);
        game.setScore(new Score(0));
        GameEntity savedGame = gameRepository.save(game);

        Game b = new Game(savedGame.getId(), gameSize, pictureRepository.getAllCellPictures());

        games.put(b.getId(), b);
        return b.getId();
    }


    /**
     *
     * @param id
     * @return Null if game doesn't exist
     */
    public Cell[][] getGame(String id) {
        if (games.containsKey(id)) {
            return games.get(id).getMatrix();
        } else {
            return null;
        }
    }


    // false - game wasn't found
    // true  - game deleted
    public boolean endGame(String gameId) {

        Game g = games.get(gameId);
        // if game doesn't exist
        if(g == null) return false;

        // remove the game from list
        games.remove(gameId);

        // game is finished
        return true;

    }

    public MatchResponse evaluateMatch(String gameId, int[] positions){
        Cell[][] matrix =  getGame(gameId);

        if(matrix != null) {
            boolean wasMatch = matchMaker.isMatch(matrix, positions);

            if(wasMatch){
                // match
                Optional<GameEntity> ge = gameRepository.findById(gameId);
                GameEntity game = ge.get();
                Score score = game.getScore();
                int a = score.getScore();
                a += 5;
                score.setScore(a);
                game.setScore(score);
                gameRepository.save(game);
                //scoreRepository.updateScore(score.getId(), a);
                // check if game is finished
                if(isEveryCellGuessed(gameId)){
                    return new MatchResponse(true, true);
                }
                else return new MatchResponse(false, true);
            }
            // no match
            else return new MatchResponse(false, false);
        }
        // if matrix == null
        return null;
    }
    public boolean isEveryCellGuessed(String gameId){
        Game game = games.get(gameId);
        // return true if game was finished before the request
        // and disappeared from the list of active games
        if(game == null) return true;
        // if game is on the list, evaluate if all cards are guessed
        return games.get(gameId).isEveryCellGuessed();
    }
}