// **********************************************************
// Copyright (c) Cengage Learning 2013 - All rights reserved
// **********************************************************
package tdd.katas.day3;

public class Cell {

    private boolean hasLife = false;
    private int liveNeighbors;

    public boolean isAlive(){
        return hasLife;
    }

    public void kill() {
        this.hasLife = false;
    }

    public void restoreLife() {
        this.hasLife = true;
    }

    protected void setLiveNeighbors(int i) {
        this.liveNeighbors = i;
    }

    public int getNumberOfLiveNeighbors() {
        return liveNeighbors;
    }
    
}
