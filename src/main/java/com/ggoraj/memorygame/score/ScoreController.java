package com.ggoraj.memorygame.score;

import com.ggoraj.memorygame.score.msg.AllScoreRequest;
import com.ggoraj.memorygame.score.msg.AllScoreResponse;
import com.ggoraj.memorygame.score.msg.CurrentScoreRequest;
import com.ggoraj.memorygame.score.msg.CurrentScoreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/game")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    // get games score
    @GetMapping("/score")
    public CompletableFuture<CurrentScoreResponse> getCurrentScore(@RequestBody CurrentScoreRequest request){

        String gameId = request.getGameId();
        int gameScore = scoreService.getGameScore(gameId);
        CurrentScoreResponse response = new CurrentScoreResponse(gameScore);

        return CompletableFuture.completedFuture(response);

    }

    // get all user score
    @GetMapping("/score/all")
    public CompletableFuture<AllScoreResponse> getCurrentScore(@RequestBody AllScoreRequest request){

        Long userId = request.getUserId();
        Long allUserScore = scoreService.getSummedUserScore(userId);
        AllScoreResponse response = new AllScoreResponse(allUserScore);
        return CompletableFuture.completedFuture(response);

    }
}
