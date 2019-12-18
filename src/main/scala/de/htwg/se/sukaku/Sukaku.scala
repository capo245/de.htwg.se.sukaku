package de.htwg.se.sukaku

import de.htwg.se.sukaku.model.{Grid, GridCreator, Player, Solver}
import de.htwg.se.sukaku.aview.Tui
import de.htwg.se.sukaku.controller.controllerComponent.controllerBaseImpl.Controller

import scala.io.StdIn.readLine

object Sukaku {
  val controller = new Controller(new Grid(9))
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = args(0)

    if (!input.isEmpty) tui.processInputLine(input)
    else do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
