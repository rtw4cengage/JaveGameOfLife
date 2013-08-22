// Copyright (c) Cengage Learning 2013 - All rights reserved
package tdd.katas.day3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameOfLifeTest {
    
    private GameOfLife game = new GameOfLife(3);

    @Before
    public void setUp(){
        setUpAGrid();
    }
    
    @Test
    public void newCellsStartOutDead() {
        Cell cell = new Cell();
        
        assertFalse( cell.isAlive() );
    }
    
    @Test
    public void cellsCanDie() {
        Cell cell = game.getCellAt(0,0);
        
        assertTrue( cell.isAlive() );
        
        cell.kill();
        
        assertFalse( cell.isAlive() );
    }
    
    @Test
    public void canBringCellBackToLife() {
        Cell cell = game.getCellAt(0,0);
        cell.kill();

        cell.restoreLife();
        
        assertTrue( cell.isAlive() );
    }
    
    @Test
    public void cellShouldDieWhenUnderPopulated(){
        Cell cell = game.getCellAt(2,0);
        cell.restoreLife();
        
        game.setCellStatusAt(2,0);
        
        assertFalse( cell.isAlive() );
    }

    @Test
    public void cellWithTwoLiveNeighborsLiveOn(){
        Cell cell = game.getCellAt(0, 0);

        game.setCellStatusAt(0,0);
        
        assertTrue( cell.isAlive() );
    }

    @Test
    public void cellWithThreeLiveNeighborsLiveOn(){
        Cell cell = game.getCellAt(1, 1);

        game.setCellStatusAt(0,0);
        
        assertTrue( cell.isAlive() );
    }
    
    @Test
    public void cellWithMoreThanThreeLiveNeighborsDies(){
        Cell cell = game.getCellAt(1, 0);
        game.getCellAt(1, 1).restoreLife();
        game.getCellAt(2, 0).restoreLife();
        game.getCellAt(2, 1).restoreLife();
        
        game.setCellStatusAt(1,0);
        
        assertFalse( cell.isAlive() );
    }
    
    @Test
    public void deadCellWithExactlyThreeLiveNeighborsComesToLife(){
        Cell cell = game.getCellAt(1, 1);
        cell.kill();
        
        game.setCellStatusAt(1,1);
        
        assertTrue( cell.isAlive() );
    }

    @Test
    public void gameCanDetermineHowManyLiveNeighborsCellHas(){
        assertEquals(3, game.findLiveNeighbors(1, 1));
    }

    
    @Test
    public void cellCanDetermineHowManyLiveNeighborsItHasWhenItIsNearAnEdge(){
        assertEquals(2, game.findLiveNeighbors(0, 0));
        assertEquals(1, game.findLiveNeighbors(2, 2));
        assertEquals(3, game.findLiveNeighbors(0, 1));
    }

    private void setUpAGrid() {
        game.addLiveCell(0,0);
        game.addLiveCell(0,1);
        game.addLiveCell(0,2);
//        game.addLiveCell(0,3);
        
        game.addCell(1,0);
        game.addLiveCell(1,1);
        game.addCell(1,2);
//        game.addCell(1,3);
        
        game.addCell(2,0);
        game.addCell(2,1);
        game.addCell(2,2);
//        game.addCell(2,3);
        
//        game.addCell(3,0);
//        game.addCell(3,1);
//        game.addCell(3,2);
//        game.addCell(3,3);
    }
    
    @Test
    public void doOneTick(){
        game.status();
        game.tick();
        game.status();

        assertTrue(game.getCellAt(0, 0).isAlive());
        assertTrue(game.getCellAt(0, 1).isAlive());
        assertTrue(game.getCellAt(0, 2).isAlive());

        assertTrue(game.getCellAt(1, 0).isAlive());
        assertTrue(game.getCellAt(1, 1).isAlive());
        assertTrue(game.getCellAt(1, 2).isAlive());
        
        assertFalse(game.getCellAt(2, 0).isAlive());
        assertFalse(game.getCellAt(2, 1).isAlive());
        assertFalse(game.getCellAt(2, 2).isAlive());
        
    }
}
