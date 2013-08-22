// **********************************************************
// Copyright (c) Cengage Learning 2013 - All rights reserved
// **********************************************************
package tdd.katas.day3;

public class GameOfLife {
    
    private int size = 0;
    private Cell grid[][];
    private Cell gridAfterTick[][];
    
    public GameOfLife(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        this.gridAfterTick = new Cell[size][size];
        for(int x=0; x<size; x++)
            for(int y=0; y<size; y++)
                gridAfterTick[x][y] = new Cell();
    }
    
    public void addCell(int x, int y) {
        Cell cell = new Cell();
        grid[x][y] = cell;
    }
    
    public void addLiveCell(int x, int y) {
        Cell cell = new Cell();
        grid[x][y] = cell;
        cell.restoreLife();
    }
    
    public Cell getCellAt(int x, int y) {
        return grid[x][y];
    }
    
    int findLiveNeighbors(int x, int y) {
        return checkRowAbove(x, y) + checkMyRow(x, y) + checkRowBelow(x, y);
    }
    
    private int checkRowBelow(int x, int y) {
        return checkCellForLife(x + 1, y - 1) + checkCellForLife(x + 1, y) + checkCellForLife(x + 1, y + 1);
    }
    
    private int checkCellForLife(int x, int y) {
        if (x<0 || y<0 ) return 0;
        if (x>size-1 || y>size-1 ) return 0;
        return grid[x][y].isAlive() ? 1 : 0;
    }
    
    private int checkMyRow(int x, int y) {
        return checkCellForLife(x, y - 1) + checkCellForLife(x, y + 1);
    }
    
    private int checkRowAbove(int x, int y) {
        return checkCellForLife(x - 1, y - 1) + checkCellForLife(x - 1, y) + checkCellForLife(x - 1, y + 1);
    }

    public void status() {
        System.out.println("==========");
        for(int x=0; x<size; x++){
            for(int y=0; y<size; y++){
                System.out.print(grid[x][y].isAlive() ? "O" : "X");
            }
            System.out.println(" ");
        }
    }

    /**
     * 
     */
    public void tick() {
        for(int x=0; x<size; x++){
            for(int y=0; y<size; y++){
                Cell cell = getCellAt(x,y);
                cell.setLiveNeighbors(findLiveNeighbors(x, y));
                
                if(this.shouldCellBeAliveAt(x,y)){
                    gridAfterTick[x][y].restoreLife();
                } else {
                    gridAfterTick[x][y].kill();
                }
            }
        }
        synchronizeGrids();
    }

    private boolean shouldCellBeAliveAt(int x, int y) {
        int liveNeighbors = findLiveNeighbors(x, y);
        if(shouldBeKilled(liveNeighbors)) {
            return false;
        }
        if(shouldComeToLife(liveNeighbors)) {
            return true;
        }
        return getCellAt(x,y).isAlive();
    }

    private boolean shouldComeToLife(int liveNeighbors) {
        return liveNeighbors == 3;
    }

    private boolean shouldBeKilled(int liveNeighbors) {
        return isUnderCrowded(liveNeighbors) || isOverCrowded(liveNeighbors);
    }

    private boolean isOverCrowded(int liveNeighbors) {
        return liveNeighbors > 3;
    }

    private boolean isUnderCrowded(int liveNeighbors) {
        return liveNeighbors < 2;
    }
    
    private void synchronizeGrids() {
        for(int x=0; x<size; x++){
            for(int y=0; y<size; y++){
                if (gridAfterTick[x][y].isAlive()) 
                    grid[x][y].restoreLife();
                else
                    grid[x][y].kill();
            }
        }
    }

    public void setCellStatusAt(int x, int y) {
        if( shouldCellBeAliveAt(x, y) ){
            getCellAt(x, y).restoreLife();
        } else {
            getCellAt(x, y).kill();
                
        }
    }
}
