package de.htwg.se.sukaku.aview

import com.typesafe.scalalogging.{LazyLogging, Logger}
import de.htwg.se.sukaku.controller.controllerComponent.ControllerInterface
import de.htwg.se.sukaku.controller.controllerComponent.GameStatus
import de.htwg.se.sukaku.controller.controllerComponent.{CandidatesChanged, CellChanged, GridSizeChanged}

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor with LazyLogging{

  listenTo(controller)
  def size = controller.gridSize
  def randomCells:Int = size*size/8

  def processInputLine(input: String):Unit = {
    input match {
      case "q" =>
      case "e" => controller.createEmptyGrid
      case "n" => controller.createNewGrid
      case "z" => controller.undo
      case "y" => controller.redo
      case "s" => controller.solve
      case "f" => controller.save
      case "l" => controller.load
      case "." => controller.resize(1)
      case "+" => controller.resize(4)
      case "#" => controller.resize(9)
      case _ => input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
          case row :: col :: value :: Nil => controller.set(row, col, value)
          case row :: col::Nil => controller.showCandidates(row, col)
          case index::Nil => controller.highlight(index)
          case _ =>
        }

    }
  }

  reactions += {
    case event: GridSizeChanged => printTui
    case event: CellChanged     => printTui
    case event: CandidatesChanged => printCandidates
  }

  def printTui: Unit = {
    logger.info(controller.gridToString)
    logger.info(GameStatus.message(controller.gameStatus))
  }

  def printCandidates: Unit = {
    logger.info("Candidates: ")
    for (row <- 0 until size; col <- 0 until size) {
      if (controller.isShowCandidates(row, col)) println("("+row+","+col+"):"+controller.available(row, col).toList.sorted)
    }
  }
}
