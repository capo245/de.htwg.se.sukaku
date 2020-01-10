package de.htwg.se.sukaku.model.gridComponent.gridBaseImpl

import de.htwg.se.sukaku.model.gridComponent.CellInterface

case class Cell(value: Int, given:Boolean = false, isHighlighted:Boolean = false, showCandidates:Boolean = false) extends CellInterface {
  def isSet: Boolean = value != 0

  override def toString: String = value.toString.replace('0', ' ') + (if(given) "." else if(isHighlighted)"'" else " ")
}

object Cell {
  import play.api.libs.json._
  implicit val cellWrites = Json.writes[Cell]
  implicit val cellReads = Json.reads[Cell]
}