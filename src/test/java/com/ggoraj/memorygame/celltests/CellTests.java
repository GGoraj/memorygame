package com.ggoraj.memorygame.celltests;

import com.ggoraj.memorygame.game.engine.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CellTests {

    private static Cell cell;

    @BeforeAll
    static void init(){
        cell = new Cell("PictureName", 0,1);
    }


    @Test
    void cellObjectCreatedWithPictureRowCol(){
        assertNotNull(cell);
    }

    @Test
    void initialCellWasGuessedPropertyIsFalse(){
        assertFalse(cell.getWasGuessed());
    }
}
