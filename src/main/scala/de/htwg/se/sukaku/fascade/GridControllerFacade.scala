package de.htwg.se.sukaku.fascade

import de.htwg.se.sukaku.model.{Grid, GridCreator}
import de.htwg.se.sukaku.util.Observable
import de.htwg.se.sukaku.subsystem.GridHandler

class GridControllerFacade(var grid:Grid) extends Observable{
    def gridfacade(size:Int, randomCells:Int, row:Int, col:Int, value:Int, gtype:String): Unit = {
        var handle = new GridHandler(grid)
        if(gtype.equals("empty"))
            handle.createEmptyGrid(size)
        if(gtype.equals("random")) {
            handle.createRandomGrid(size, randomCells)
        if(gtype.equals("set"))
            handle.set(row, col, value)
        }
    }



}
