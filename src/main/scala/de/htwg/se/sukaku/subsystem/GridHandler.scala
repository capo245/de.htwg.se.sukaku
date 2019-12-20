package de.htwg.se.sukaku.subsystem

import de.htwg.se.sukaku.controller.controllerComponent.GameStatus._
import de.htwg.se.sukaku.controller.controllerComponent._
import de.htwg.se.sukaku.model.gridComponent.GridInterface
import de.htwg.se.sukaku.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.sukaku.util.UndoManager
import de.htwg.se.sukaku.controller.controllerComponent.ControllerInterface
import de.htwg.se.sukaku.controller.controllerComponent.controllerBaseImpl.SetCommand
import de.htwg.se.sukaku.model.gridComponent.GridInterface


import scala.swing.Publisher

class GridHandler(var grid: GridInterface) {
  var gameStatus: GameStatus = IDLE
  var showAllCandidates: Boolean = false
  private val undoManager = new UndoManager

  def createEmptyGrid(size: Int): GridInterface ={
    new Grid(size)
  }

  def createRandomGrid(size: Int, randomCells: Int): GridInterface = {
    grid.createNewGrid(size)
  }

  def resize(newSize:Int):GridInterface = {
    new Grid(newSize)
  }

  def gridToString: String = grid.toString


}
