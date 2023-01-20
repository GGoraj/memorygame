package com.ggoraj.memorygame.game.msg;


import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class MatchRequest {

    private String gameId;
    private int[] positions;

}
