package de.htwg.se.sukaku.aview.gui

import scala.swing._
import scala.swing.event._

import de.htwg.se.sukaku.controller.controllerComponent.{CellChanged, ControllerInterface}

class CellPanel(row: Int, column: Int, controller: ControllerInterface) extends FlowPanel {

  val givenCellColor = new Color(100, 120, 155)
  val cellColor = new Color(124, 224, 155)
  val highlightedCellColor = new Color(192, 55, 92)

  def myCell = controller.cell(row, column)

  def cellText(row: Int, col: Int) = if (controller.isSet(row, column)) " " + controller.cell(row, column).value.toString else " "

  val label =
    new Label {
      text = cellText(row, column)
      font = new Font("SansSerif", 1, 36)
    }

  val cell = new BoxPanel(Orientation.Vertical) {
    contents += label
    preferredSize = new Dimension(75, 40)
    background = if (controller.isGiven(row, column)) givenCellColor else cellColor
    border = Swing.BeveledBorder(Swing.Lowered)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case e: CellChanged => {
        label.text = cellText(row, column)
        repaint
      }
      case MouseClicked(src, pt, mod, clicks, pops) => {
        controller.showCandidates(row, column)
        repaint
      }
    }
  }

  val candidatelist = (1 to controller.gridSize).map {
    (value =>
      new Label {
        text = if (controller.available(row, column).contains(value)) value.toString else " "
        //geht nicht
        preferredSize = new Dimension(17, 47)
        font = new Font("Verdana", 1, 9)
        background = cellColor
        border = Swing.BeveledBorder(Swing.Raised)
        listenTo(mouse.clicks)
        listenTo(controller)
        reactions += {
          case e: CellChanged => {
            text = if (controller.available(row, column).contains(value)) value.toString else " "
            repaint
          }
          case MouseClicked(src, pt, mod, clicks, pops) => {
            controller.set(row, column, value)
            text = if (controller.available(row, column).contains(value)) value.toString else " "
            repaint
          }
        }
      })
  }
  val candidates = new GridPanel(controller.blockSize, controller.blockSize) {
    setBackground(this)
    contents ++= candidatelist
  }
  contents += candidates

  def redraw = {
    contents.clear()
    if ((controller.isShowCandidates(row, column) || controller.showAllCandidates) && !controller.isSet(row, column)) {
      setBackground(candidates)
      contents += candidates
    } else {
      label.text = cellText(row, column)
      setBackground(cell)
      contents += cell
    }
    repaint
  }

  def setBackground(p: Panel) = p.background = if (controller.isGiven(row, column)) givenCellColor
  else if (controller.isHighlighted(row, column)) highlightedCellColor
  else cellColor

}