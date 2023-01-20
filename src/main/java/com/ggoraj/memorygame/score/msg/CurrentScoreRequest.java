package com.ggoraj.memorygame.score.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrentScoreRequest {
    private String gameId;
}
