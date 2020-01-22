package de.htwg.se.sukaku

import com.google.inject.Guice
import de.htwg.se.sukaku.aview.Tui
import de.htwg.se.sukaku.aview.gui.SwingGui
import de.htwg.se.sukaku.controller.controllerComponent.ControllerInterface

import scala.io.StdIn.readLine

object Sukaku {
  val injector = Guice.createInjector(new SukakuModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  controller.createNewGrid

  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
