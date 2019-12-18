package de.htwg.se.sukaku.util

trait Command {

  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit

}
