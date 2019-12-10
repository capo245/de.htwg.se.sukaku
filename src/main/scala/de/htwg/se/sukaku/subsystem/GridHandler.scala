package de.htwg.se.sukaku.subsystem

import de.htwg.se.sukaku.model.{Grid, GridCreator}
import de.htwg.se.sukaku.util.Observable

class GridHandler(var grid:Grid) extends Observable {
    def createEmptyGrid(size: Int):Unit = {
      grid = new Grid(size)
      notifyObservers
    }
    def createRandomGrid(size: Int, randomCells:Int):Unit = {
      grid = new GridCreator(size).createRandom(randomCells)
      notifyObservers
    }

    def gridToString: String = grid.toString

    def set(row: Int, col: Int, value: Int):Unit = {
      grid = grid.set(row, col, value)
      notifyObservers
    }

}
