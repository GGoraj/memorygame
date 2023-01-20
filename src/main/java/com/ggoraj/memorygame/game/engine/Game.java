package com.ggoraj.memorygame.game.engine;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Game extends Board {
    private String id;
    private String[] pictures;
    private Cell[][] matrix;
    private boolean isDone;


    // board is a square so size is an even number
    private int size;
    private int score;


    public Game(){
        //empty
    }

    public Game(String id, int size, String[] pictures) {
        super(size, pictures);
        matrix = super.getMatrix();
        this.id = id;
        this.size = matrix.length;
        this.isDone = false;
    }


    /**
     * register match setting true at the guessed property in 2 cells.
     * method takes an array parameter
     * array of size 4
     * first 2 int are positions of first Cell
     * second 2 of another
     *
     * @return true if match was registered
     */
    // register a match setting true at the guessed property in 2 cells.
    // method takes an array parameter,
    //
    public boolean setMatch(int[] positions) {
        // if number of positions is <4 or >4
        if (positions.length != 4) {
            return false;
        }

        // check if any position is > then row or column count in matrix
        for (int i : positions
        ) {
            if (i > size - 1) return false;
        }

        int row1 = positions[0];
        int column1 = positions[1];
        int row2 = positions[2];
        int column2 = positions[3];

        // prepare cells to compare
        Cell cell1 = matrix[row1][column1];
        Cell cell2 = matrix[row2][column2];

        // check if match wasn't done before
        if (cell1.getWasGuessed() || cell2.getWasGuessed()) {
            return false;
        }

        // double check for match
        if (!cell1.getPicture().equals(cell2.getPicture())) {
            return false;
        } else {
            // finally set guessed property true
            // and return true
            matrix[row1][column1].setWasGuessed(true);
            matrix[row2][column2].setWasGuessed(true);
            return true;

        }
    }


    public boolean isEveryCellGuessed() {
        int guessedCounter = 0;
        int tileNumber = size * size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].getWasGuessed() == true) guessedCounter++;
            }
        }
        if (guessedCounter == tileNumber) {
            isDone = true;
            return true;
        } else return false;
    }



    @Override
    public Cell[][] getMatrix() {
        return matrix;
    }


}