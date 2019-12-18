package de.htwg.se.sukaku

import de.htwg.se.sukaku.aview.Tui
import de.htwg.se.sukaku.aview.gui.SwingGui
import de.htwg.se.sukaku.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.sukaku.model.gridComponent.gridAdvancedImpl.Grid

import scala.io.StdIn.readLine

object Sukaku {
  val defaultsize=9
  val controller = new Controller(new Grid(defaultsize))
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  controller.createRandomGrid(9,9)

  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
