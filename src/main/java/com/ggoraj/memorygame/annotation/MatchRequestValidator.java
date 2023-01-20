package com.ggoraj.memorygame.annotation;

import com.ggoraj.memorygame.game.engine.Cell;
import com.ggoraj.memorygame.game.GameService;
import com.ggoraj.memorygame.game.msg.MatchRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchRequestValidator implements ConstraintValidator<MatchRequestConstraint, MatchRequest> {

    @Autowired
    GameService gameService;

    @Override
    public void initialize(MatchRequestConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MatchRequest request, ConstraintValidatorContext context) {

        int[] positions = request.getPositions();
        String gameId = request.getGameId();
        Cell[][] matrix = gameService.getGame(gameId);

        // check if game exists
        if(matrix == null) return false;

        // check positions constrains
        boolean valid = true;
        for (int i: positions
        ) {
            if(i<0 || i>4) {
                valid = false;
                break;
            }
        }

        return valid;
    }
}
