package com.ggoraj.memorygame.game.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MatchResponse {
    private boolean isGameFinished;
    private boolean isMatch;


}
