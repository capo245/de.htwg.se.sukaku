package de.htwg.se.sukaku.model.fileIoComponent

import de.htwg.se.sukaku.model.gridComponent.GridInterface

trait FileIOInterface {

  def load:Option[GridInterface]
  def save(grid:GridInterface):Unit

}
