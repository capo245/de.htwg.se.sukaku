package de.htwg.se.sukaku.model


case class Cell(value: Int) {
  def isSet: Boolean = value != 0
}