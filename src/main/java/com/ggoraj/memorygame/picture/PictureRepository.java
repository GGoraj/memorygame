package com.ggoraj.memorygame.picture;

import org.springframework.stereotype.Repository;

@Repository
public class PictureRepository {
    private String[] pictures = new String[] {"apple","car", "banana", "ball", "chair", "cow", "cat", "cola", "panda", "lamp", "screwdriver"};


    public String[] getAllCellPictures(){
        return pictures;
    }
}
