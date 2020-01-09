package de.htwg.se.sukaku

import com.google.inject.{Guice, Injector}
import de.htwg.se.sukaku.aview.Tui
import de.htwg.se.sukaku.aview.gui.SwingGui
import de.htwg.se.sukaku.controller.controllerComponent.ControllerInterface
import de.htwg.se.sukaku.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.sukaku.model.gridComponent.gridAdvancedImpl.Grid

import scala.io.StdIn.readLine
//Bug punkte weg machen

object Sukaku {
  //moved to module
  //val defaultsize=9
  val injector: Injector = Guice.createInjector(new SukakuModule: SukakuModule)
  //val controller = new Controller(new Grid(defaultsize))
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  //controller.createRandomGrid(9,9)
  controller.createNewGrid
  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
