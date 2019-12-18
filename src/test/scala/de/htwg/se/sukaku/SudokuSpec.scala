package de.htwg.se.sukaku

import org.scalatest.{Matchers, WordSpec}

class SudokuSpec extends WordSpec with Matchers {

  "The Sudoku main class" should {
    "accept text input as argument without readline loop, to test it from command line " in {
      Sukaku.main(Array[String]("s"))
    }
  }

}
