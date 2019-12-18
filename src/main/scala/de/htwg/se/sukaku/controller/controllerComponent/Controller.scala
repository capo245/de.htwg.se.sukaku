package de.htwg.se.sukaku.controller.controllerComponent

import de.htwg.se.sukaku.fascade.GridControllerFacade
import de.htwg.se.sukaku.model.{Grid, Solver}
import de.htwg.se.sukaku.util.Observable

class Controller(var grid:Grid) extends Observable{
  var facade = new GridControllerFacade(grid)

  def createEmptyGrid(size: Int):Unit = {
//    grid = new Grid(size)
    facade.gridfacade(size, 0, 0, 0, 0, "empty")
    notifyObservers
  }

  def createRandomGrid(size: Int, randomCells:Int):Unit = {
//    grid = new GridCreator(size).createRandom(randomCells)
    facade.gridfacade(size, randomCells, 0, 0, 0, "random")
    notifyObservers
  }

  def gridToString: String = grid.toString

  def set(row: Int, col: Int, value: Int):Unit = {
//    grid = grid.set(row, col, value)
      facade.gridfacade(0, 0, row, col, value, "set")
      notifyObservers
  }

  def solve: Boolean = {

    val (success, g) = new Solver(grid).solve
    grid = g
    notifyObservers
    success
  }

}
