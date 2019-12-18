package de.htwg.se.sukaku.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.sukaku.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.sukaku.util.Observer

import scala.language.reflectiveCalls
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "empty" should {
      val smallGrid = new Grid(4)
      val controller = new Controller(smallGrid)
      "handle undo/redo of solving a grid correctly" in {
        controller.grid.cell(0, 0).isSet should be(false)
        controller.grid.solved should be(false)
        controller.solve
        controller.grid.cell(0, 0).isSet should be(true)
        controller.grid.solved should be(true)
        controller.undo
        controller.grid.cell(0, 0).isSet should be(false)
        controller.grid.solved should be(false)
        controller.redo
        controller.grid.cell(0, 0).isSet should be(true)
        controller.grid.solved should be(true)
      }
    }
  }
}

