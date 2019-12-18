package de.htwg.se.sukaku.aview

import de.htwg.se.sukaku.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.sukaku.model.gridComponent.gridBaseImpl.Grid

import de.htwg.se.sukaku.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.sukaku.model.gridComponent.gridBaseImpl.Grid

import org.scalatest.{Matchers, WordSpec}

class TuiSpec  extends WordSpec with Matchers{

  "A Sukaku Tui" should {
    val controller = new Controller(new Grid(9))
    val tui = new Tui(controller)
    "create and empty Sukaku on input 'n'" in {
      tui.processInputLine("n")
      controller.grid should be(new Grid(9))
    }
    "set a cell on input '123'" in {
      tui.processInputLine("123")
      controller.grid.cell(1,2).value should be(3)
    }
    "create a random Sukaku on input 'r'" in {
      tui.processInputLine("r")
      controller.grid.valid should be(true)
    }
    "solve a Sukaku on input 's'" in {
      tui.processInputLine("n")
      tui.processInputLine("s")
      controller.grid.solved should be(true)
    }
  }

}
