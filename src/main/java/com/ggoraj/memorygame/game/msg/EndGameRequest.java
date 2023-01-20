package com.ggoraj.memorygame.game.msg;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EndGameRequest {
    @NotBlank
    private String gameId;

}
