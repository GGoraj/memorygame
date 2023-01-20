package com.ggoraj.memorygame.gametests;


import com.ggoraj.memorygame.game.engine.Cell;
import com.ggoraj.memorygame.game.engine.Game;
import com.ggoraj.memorygame.picture.PictureRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GameTests {

    private static Game game;


    @BeforeAll
    static void init(@Autowired PictureRepository pictureRepository){
        String[] pictures = pictureRepository.getAllCellPictures();
        String uuid = String.valueOf(UUID.randomUUID());
        game = new Game(uuid, 4, pictures);
    }

    @org.junit.Test
    void boardSizeIsAlwaysAnEvenNumber(){
        boolean isEven = false;
        if(game.getSize() % 2 == 0) isEven = true;
        assertTrue(isEven);
    }

    @Test
    void eachBoardHasUniqueId(){
        String id = game.getId();
        // regex of UUID
        String pattern = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        boolean match = Pattern.matches(pattern, id);
        assertTrue(match);
    }

   @Test
    void initialScoreIsZero(){
        int score = game.getScore();
        assertTrue(score == 0);
    }

    @Test
    void getBoardReturns2DimentionalArray(){
        assertTrue(game.getMatrix() instanceof Cell[][]);
    }

    @Test
    void eachCellInBoardHasAPictureAssigned(){

        int size = game.getSize();
        Cell[][] matrix = game.getMatrix();

        boolean isEmpty = false;
        for(int i = 0; i< size; i++){
            for(int j=0; j<size; j++){
                if(matrix[i][j].getPicture().equals(null) ||
                matrix[i][j].getPicture().equals("")){
                    isEmpty = true;
                }
            }
        }
        assertFalse(isEmpty);

    }

    @Test
    void setMatch_False_PositionsArrayTooLong(){
        int[] positions = new int[]{1,2,2,3,5};
        boolean isMatch = game.setMatch(positions);
        assertFalse(isMatch);
    }

    @Test
    void setMatch_False_PositionsArrayTooShort(){
        int[] positions = new int[]{1,2,2};
        boolean isMatch = game.setMatch(positions);
        assertFalse(isMatch);
    }

    @Test
    void setMatch_False_GivenPositionsExceedMatrixLength(){
        int size = game.getSize();
        int[] positions = new int[]{1,2,4,6};
        boolean isMatch = game.setMatch(positions);
        assertFalse(isMatch);

    }


    @Test
    void setMatch_True_AllGivenDataIsCorrectAndMatch(){
        int size = game.getSize();
        Cell[][] matrix = game.getMatrix();
        int[] positions = new int[4];
        String picture1 = matrix[0][0].getPicture();
        positions[0] = 0;
        positions[1] = 0;

        for(int i=0; i< size; i++){
            for(int j=1; j< size; j++){
                if(matrix[i][j].getPicture().equals(picture1)){
                    positions[2] = i;
                    positions[3] = j;
                    break;
                }
            }
        }

        boolean isMatch = game.setMatch(positions);
        assertTrue(isMatch);
    }

    @Test
    void setMatch_True_PositionsCorrectMatchMadeWasGuessedSetToTrue(){
        int size = game.getSize();
        Cell[][] matrix = game.getMatrix();
        int[] positions = new int[4];
        String picture1 = matrix[0][0].getPicture();
        positions[0] = 0;
        positions[1] = 0;

        for(int i=0; i< size; i++){
            for(int j=1; j< size; j++){
                if(matrix[i][j].getPicture().equals(picture1)){
                    positions[2] = i;
                    positions[3] = j;
                    break;
                }
            }
        }

        game.setMatch(positions);
        assertTrue(matrix[0][0].getWasGuessed());
        int row2 = positions[2];
        int column2 = positions[3];
        assertTrue(matrix[row2][column2].getWasGuessed());
    }

    @Test
    void isGameFinished_False_NotAllCellsInBoardAreGuessed(){
        String id = game.getId();
        boolean isFinished = game.isEveryCellGuessed();
        assertFalse(isFinished);
    }

    @Test
    void isGameFinished_True_allCellsInBoardAreGuessed(){
        String id = game.getId();
        Cell[][] matrix = game.getMatrix();
        // simulate that each cell was guessed
        for(int i=0; i< matrix.length; i++){
            for (int j=0; j< matrix.length; j++){
                matrix[i][j].setWasGuessed(true);
            }
        }
        boolean isFinished = game.isEveryCellGuessed();
        assertTrue(isFinished);

    }





}
