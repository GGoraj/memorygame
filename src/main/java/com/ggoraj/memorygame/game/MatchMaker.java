package com.ggoraj.memorygame.game;

import com.ggoraj.memorygame.game.engine.Cell;
import org.springframework.stereotype.Component;

@Component
public class MatchMaker {


    public MatchMaker(){}

    /**
     * register match setting true at the guessed property in 2 cells.
     * method takes an array parameter
     * first 2 int are positions of first Cell
     * second 2 of another
     * @return true if match was registered
     */
    public boolean isMatch(Cell[][] matrix, int[] positions){

        int length = matrix.length;

        // if number of positions is <length or >length
        if(positions.length != length) {
            return false;
        }

        // check if any position is > then row or column count in matrix
        for (int i: positions
        ) {
            if(i > length-1) return false;
        }

        int row1 = positions[0];
        int column1 = positions[1];
        int row2 = positions[2];
        int column2 = positions[3];

        // prepare cells to compare
        Cell cell1 = matrix[row1][column1];
        Cell cell2 = matrix[row2][column2];


        // check for match
        if(!cell1.getPicture().equals(cell2.getPicture())){
            return false;
        }
        else{
            // finally set guessed property true
            // and return true
            matrix[row1][column1].setWasGuessed(true);
            matrix[row2][column2].setWasGuessed(true);
            return true;
        }
    }

}
