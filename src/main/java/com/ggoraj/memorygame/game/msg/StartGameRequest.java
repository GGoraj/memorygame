package com.ggoraj.memorygame.game.msg;

import com.ggoraj.memorygame.annotation.MatrixSizeConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StartGameRequest {

    @NotNull(message = "Please enter id")
    private Long userId;

    @MatrixSizeConstraint
    private int size;


}
