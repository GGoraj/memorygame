package com.ggoraj.memorygame.game;


import com.ggoraj.memorygame.game.engine.Cell;
import com.ggoraj.memorygame.game.msg.EndGameRequest;
import com.ggoraj.memorygame.game.msg.MatchRequest;
import com.ggoraj.memorygame.game.msg.MatchResponse;
import com.ggoraj.memorygame.game.msg.StartGameRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/game")
public class GameController {

    @Autowired
    GameService gameService;

    @Async
    @PostMapping("/start")
    // returns board id
    public CompletableFuture startGame(@Valid @RequestBody StartGameRequest request){

        int size = request.getSize();
        Long userId = request.getUserId();

        String gameId = gameService.startGame(userId, size);
        //return CompletableFuture.completedFuture(fractionService.getCashGainOfSingleFraction(fraction_id));
        return CompletableFuture.completedFuture(gameId);
    }

    @Async
    @PostMapping("/end")
    // returns board id
    public CompletableFuture endGame(@Valid @RequestBody EndGameRequest request){

        String gameId = request.getGameId();

        boolean result = gameService.endGame(gameId);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    @PostMapping("/{id}")
    public CompletableFuture getGame(@Valid @PathVariable("id") String id){

        Cell[][] board = gameService.getGame(id);
        if(board != null) {
            return CompletableFuture.completedFuture(board);
        }
        else return CompletableFuture.completedFuture(HttpStatusCode.valueOf(400));
    }

    @Async
    @PostMapping("/finished/{id}")
    public CompletableFuture isGameFinished(@Valid @PathVariable("id") String gameId){
        boolean result = gameService.isEveryCellGuessed(gameId);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    @PostMapping("/evaluate")
    public CompletableFuture evaluateMatch(@Valid @RequestBody MatchRequest requestBody){
        int[] positions = requestBody.getPositions();
        String gameId = requestBody.getGameId();
        MatchResponse response = gameService.evaluateMatch(gameId, positions);
        if(response != null) {
            return CompletableFuture.completedFuture(response);
        }
        else return CompletableFuture.completedFuture(HttpStatusCode.valueOf(400));
    }
}
