package de.htwg.se.sukaku.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.sukaku.SukakuModule
import de.htwg.se.sukaku.model.fileIoComponent.FileIOInterface
import de.htwg.se.sukaku.model.gridComponent.GridInterface

import scala.xml._

class FileIO extends FileIOInterface {

  override def load: GridInterface = {
    var grid: GridInterface = null
    val file = XML.loadFile("grid.xml")
    val sizeAttr = (file \\ "grid" \ "@size")
    val size = sizeAttr.text.toInt
    val injector = Guice.createInjector(new SukakuModule)
    size match {
      case 1 => grid = injector.instance[GridInterface](Names.named("tiny"))
      case 4 => grid = injector.instance[GridInterface](Names.named("small"))
      case 9 => grid = injector.instance[GridInterface](Names.named("normal"))
      case _ =>
    }
    val cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val value: Int = cell.text.trim.toInt
      grid = grid.set(row, col, value)
      val given = (cell \ "@given").text.toBoolean
      val showCandidates = (cell \ "@showCandidates").text.toBoolean
      //if (given) grid = grid.setGiven(row, col, value)
      if (showCandidates) grid = grid.setShowCandidates(row, col)
    }
    grid
  }

  def save(grid: GridInterface): Unit = saveString(grid)

  def saveXML(grid: GridInterface): Unit = {
    XML.save("grid.xml", gridToXml(grid))
  }

  def saveString(grid: GridInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gridToXml(grid))
    pw.write(xml)
    pw.close
  }
  def gridToXml(grid: GridInterface) = {
    <grid size={ grid.size.toString }>
      {
      for {
        row <- 0 until grid.size
        col <- 0 until grid.size
      } yield cellToXml(grid, row, col)
      }
    </grid>
  }

  def cellToXml(grid: GridInterface, row: Int, col: Int) = {
    <cell row={ row.toString } col={ col.toString } given={ grid.cell(row, col).given.toString } isHighlighted={ grid.isHighlighted(row, col).toString } showCandidates={ grid.cell(row, col).showCandidates.toString }>
      { grid.cell(row, col).value }
    </cell>
  }

}
