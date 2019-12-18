package de.htwg.se.sukaku.model.gridComponent.gridBaseImpl

import org.scalatest.{Matchers, WordSpec}

class GridCreateRandomStrategySpec extends WordSpec with Matchers {

  "A GridCreator " should {
    "create an empty Grid and fill it with cells with a creation strategy" in {
      val tinyGrid = (new GridCreateRandomStrategy).createNewGrid(1)
      tinyGrid.cell(0,0).value should be(1)

      val smallGrid = (new GridCreateRandomStrategy).createNewGrid(4)
      smallGrid.valid should be(true)

      val normalGrid = (new GridCreateRandomStrategy).createNewGrid(9)
      normalGrid.valid should be(true)
    }
  }

}
