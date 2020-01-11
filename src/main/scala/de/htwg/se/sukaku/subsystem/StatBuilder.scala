package de.htwg.se.sukaku.subsystem
//Type safe builder pattern
import java.util.{Calendar, Date}

import de.htwg.se.sukaku.subsystem
sealed trait GameInfo
object GameInfo {
  sealed trait Empty extends GameInfo
  sealed trait Name extends GameInfo
  sealed trait Playtime extends GameInfo
  sealed trait DateOfSession extends GameInfo


  type MandatoryInfo = Empty with Name with Playtime with DateOfSession

}

case class GameStatBuilder[I <: GameInfo](Name: String = "",  Playtime: BigInt = 0, DateOfSession:  Date = new Date())
{


  def withName(name: String): GameStatBuilder[I with subsystem.GameInfo.Name] =
    this.copy(Name = name)

  def withPlaytime(time: BigInt): GameStatBuilder[I with subsystem.GameInfo.Playtime] =
    this.copy(Playtime = time)

  def withDateOfSession(today: Date): GameStatBuilder[I with subsystem.GameInfo.DateOfSession] =
    this.copy(DateOfSession = today)


  def build(implicit ev: I =:= subsystem.GameInfo.MandatoryInfo): GameStat =
    GameStat(Name, Playtime, DateOfSession)

  override def toString: String = Name.toString.replace(' ', '_')
}

case class GameStat(name: String,  playtime: BigInt, date: Date)


/*
object GameStat {
  import play.api.libs.json._
  implicit val statWrites = Json.writes[GameStat]
  implicit val statReads = Json.reads[GameStat]
}
 */