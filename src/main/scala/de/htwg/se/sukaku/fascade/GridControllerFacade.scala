package de.htwg.se.sukaku.fascade

import de.htwg.se.sukaku.controller.controllerComponent.GameStatus._
import de.htwg.se.sukaku.controller.controllerComponent._
import de.htwg.se.sukaku.model.gridComponent.GridInterface
import de.htwg.se.sukaku.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.sukaku.util.UndoManager
import de.htwg.se.sukaku.util.Observable
import de.htwg.se.sukaku.subsystem.GridHandler
import de.htwg.se.sukaku.model.gridComponent.GridInterface

import scala.swing.Publisher

class GridControllerFacade(var grid: GridInterface) {
  def gridfacade(size:Int, randomCells:Int, row:Int, col:Int, value:Int, gtype:String): Unit = {
    var handle = new GridHandler(grid)
    if(gtype.equals("empty"))
      handle.createEmptyGrid(size)
    if(gtype.equals("random")) {
      handle.createRandomGrid(size, randomCells)
    }
  }



}