package de.htwg.se.sukaku.model.gridComponent
import play.api.libs.json._

trait GridInterface {

  def cell(row: Int, col: Int): CellInterface
  def set(row:Int, col:Int, value:Int): GridInterface
  def reset(row:Int, col:Int): GridInterface
  def highlight(index: Int):GridInterface
  def setShowCandidates(row: Int, col: Int): GridInterface
  def createNewGrid(size:Int):GridInterface
  def solve:(Boolean, GridInterface)
  def valid:Boolean
  def isSymmetric:Boolean
  def solved:Boolean

  def size: Int

  def isHighlighted(row: Int, col: Int): Boolean
  def available(row: Int, col: Int): Set[Int]
  def indexToRowCol(index: Int):(Int, Int)
  def markFilledCellsAsGiven:GridInterface

}

trait CellInterface {
  def value:Int
  def showCandidates: Boolean
  def given: Boolean
  def isSet: Boolean

}
