package com.ggoraj.memorygame.game.engine;


import java.security.InvalidParameterException;
import java.util.Random;

public class Board {

    private int size;
    private Cell[][] matrix;
    private Random random;
    private String[] pictures;


    public Board(int size, String[] pictures){
        boolean isSizeValid = isRequestedSizeValid(size);
        if(!isSizeValid) throw new InvalidParameterException("size of the game has to be an even number");

        this.size = size;
        random = new Random();
        this.pictures = pictures;
        matrix = new Cell[size][size];
        populateMatrix();
    }

    public Board(){

    }




    private void populateMatrix(){
        // populate the matrix, while empty cells left
        // pick an image
        // generate 2 random numbers representing cells in the board
        // assign numbers to the image

        while(!isBoardFull()){

            // get random picture
            int i = random.nextInt(pictures.length);
            String picture = pictures[i];

            // get random empty position
            int[] cellPosition1 = getCellPosition();
            matrix[cellPosition1[0]][cellPosition1[1]] = new Cell(picture, cellPosition1[0],cellPosition1[1]);

            int[] cellPosition2 = getCellPosition();
            matrix[cellPosition2[0]] [cellPosition2[1]] = new Cell(picture, cellPosition2[0], cellPosition2[1]);

        }
    }

    // returns a sequence of 4 random integers
    private int[] getCellPosition(){

        if(isBoardFull() == true) return null;
        int[] rowCol = new int[2];

        // keep generating until cell is available
        while(!isBoardFull()){
            int row = random.nextInt(size);
            int column = random.nextInt(size);
            if(matrix[row][column] == null) {
                rowCol[0] = row;
                rowCol[1] = column;
                break;
            }

        }

        return rowCol;
    }

    private boolean isBoardFull(){

        for(int i=0; i< size; i++){
            for(int j=0; j< size; j++){

                if(matrix[i][j] == null){
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {

        String result = "";

        for(int i=0; i< size; i++){
            for(int j=0; j< size; j++){
                result += matrix[i][j].getPicture() + " ";
            }
            result += "\n";
        }

        return result;
    }

    private boolean isMatrixFull(){

        for(int i=0; i< size; i++){
            for(int j=0; j< size; j++){

                if(matrix[i][j] == null){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isRequestedSizeValid(int size){

        if(size % 2 != 0) {

            return false;
        }
        else return true;
    }


    public Cell[][] getMatrix(){
        return matrix;
    }

}
