package com.ggoraj.memorygame.game.engine;


public class Cell {

    private String picture;
    private int row;
    private int column;
    private boolean wasGuessed;



    public Cell(String picture, int row, int column) {
        this.picture = picture;
        this.row = row;
        this.column = column;
        this.wasGuessed = false;
    }

    //
    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean wasGuessed() {
        return this.wasGuessed;
    }

    public boolean getWasGuessed() {
        return this.wasGuessed;
    }

    public void setWasGuessed(boolean wasGuessed) {
        this.wasGuessed = wasGuessed;
    }

}
