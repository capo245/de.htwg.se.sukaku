package de.htwg.se.sukaku.controller.controllerComponent.controllerBaseImpl

import com.google.inject.name.Names
import com.google.inject.{ Guice, Inject }
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.sukaku.SukakuModule
import de.htwg.se.sukaku.controller.controllerComponent.GameStatus._
import de.htwg.se.sukaku.controller.controllerComponent._
import de.htwg.se.sukaku.model.fileIoComponent.FileIOInterface
import de.htwg.se.sukaku.model.gridComponent.GridInterface
import de.htwg.se.sukaku.util.UndoManager

import scala.swing.Publisher

class Controller @Inject() (var grid: GridInterface) extends ControllerInterface with Publisher with ControllerIoInterface {

  var gameStatus: GameStatus = IDLE
  var showAllCandidates: Boolean = false
  private val undoManager = new UndoManager
  val injector = Guice.createInjector(new SukakuModule)
  val fileIo = injector.instance[FileIOInterface]

  def createEmptyGrid: Unit = {
    grid.size match {
      case 1 => grid = injector.instance[GridInterface](Names.named("tiny"))
      case 4 => grid = injector.instance[GridInterface](Names.named("small"))
      case 9 => grid = injector.instance[GridInterface](Names.named("normal"))
      case _ =>
    }
    publish(new CellChanged)
  }

  def resize(newSize: Int): Unit = {
    newSize match {
      case 1 => grid = injector.instance[GridInterface](Names.named("tiny"))
      case 4 => grid = injector.instance[GridInterface](Names.named("small"))
      case 9 => grid = injector.instance[GridInterface](Names.named("normal"))
      case _ =>
    }
    gameStatus = RESIZE
    publish(new GridSizeChanged(newSize))
  }

  override def createNewGrid: Unit = {
    grid.size match {
      case 1 => grid = injector.instance[GridInterface](Names.named("tiny"))
      case 4 => grid = injector.instance[GridInterface](Names.named("small"))
      case 9 => grid = injector.instance[GridInterface](Names.named("normal"))
      case _ =>
    }
    grid = grid.createNewGrid
    gameStatus = NEW
    publish(new CellChanged)
  }

  def gridToString: String = grid.toString

  def set(row: Int, col: Int, value: Int): Unit = {
    undoManager.doStep(new SetCommand(row, col, value, this))
    gameStatus = SET
    publish(new CellChanged)
  }

  def solve: Unit = {
    undoManager.doStep(new SolveCommand(this))
    gameStatus = SOLVED
    publish(new CellChanged)
  }

  def save: Unit = {
    fileIo.save(grid)
    gameStatus = SAVED
    publish(new CellChanged)
  }

  def load: Unit = {
    val gridOption = fileIo.load
    gridOption match {
      case None => {
        createEmptyGrid
        gameStatus = COULDNOTLOAD
      }
      case Some(_grid) => {
        grid = _grid
        gameStatus = LOADED
      }
    }
    publish(new CellChanged)
  }

  def undo: Unit = {
    undoManager.undoStep
    gameStatus = UNDO
    publish(new CellChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus = REDO
    publish(new CellChanged)
  }

  def cell(row: Int, col: Int) = grid.cell(row, col)

  def isGiven(row: Int, col: Int): Boolean = grid.cell(row, col).given
  def isSet(row: Int, col: Int): Boolean = grid.cell(row, col).isSet
  def available(row: Int, col: Int): Set[Int] = grid.available(row, col)
  def showCandidates(row: Int, col: Int): Unit = {
    grid = grid.setShowCandidates(row, col)
    gameStatus = CANDIDATES
    publish(new CandidatesChanged)
  }

  def isShowCandidates(row: Int, col: Int): Boolean = grid.cell(row, col).showCandidates
  def gridSize: Int = grid.size
  def blockSize: Int = Math.sqrt(grid.size).toInt
  def isShowAllCandidates: Boolean = showAllCandidates
  def toggleShowAllCandidates: Unit = {
    showAllCandidates = !showAllCandidates
    gameStatus = CANDIDATES
    publish(new CellChanged)
  }
  def isHighlighted(row: Int, col: Int): Boolean = grid.isHighlighted(row, col)
  def statusText: String = GameStatus.message(gameStatus)
  def highlight(index: Int): Unit = {
    grid = grid.highlight(index)
    publish(new CellChanged)
  }

  override def setGiven(row: Int, col: Int, value: Int): Unit = {
    grid = grid.setGiven(row, col, value)
  }

  override def setShowCandidates(row: Int, col: Int): Unit = {
    grid = grid.setShowCandidates(row, col)
  }
}
