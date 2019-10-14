package de.htwg.se.sukaku

import de.htwg.se.sukaku.model.Player

object Sukaku {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}