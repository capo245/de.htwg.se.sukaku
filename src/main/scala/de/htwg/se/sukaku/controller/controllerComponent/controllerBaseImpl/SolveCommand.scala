package de.htwg.se.sukaku.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.sukaku.model.gridComponent.GridInterface
import de.htwg.se.sukaku.util.Command
import de.htwg.se.sukaku.controller.controllerComponent.GameStatus._

class SolveCommand(controller: Controller) extends Command {
  var memento: GridInterface = controller.grid
  override def doStep: Unit = {
    memento = controller.grid
    val (success, newgrid) = controller.grid.solve
    if (success) controller.gameStatus = SOLVED else controller.gameStatus= NOT_SOLVABLE
    controller.grid = newgrid
  }

  override def undoStep: Unit = {
    val new_memento = controller.grid
    controller.grid = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    val new_memento = controller.grid
    controller.grid = memento
    memento = new_memento
  }

}

