package de.htwg.se.sukaku.model

import de.htwg.se.sukaku.model.playerComponent.Player
import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Your Name")
    "have a name"  in {
      player.name should be("Your Name")
    }
    "have a nice String representation" in {
      player.toString should be("Your Name")
    }
  }}


}
