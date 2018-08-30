package exercise1

object PatternMatching {

  // a) if `value` is longer than 10 signs return it else return "String too short"
  def testLongString(value: String): String = value

  // b) if `value` is even return true else false
  def testIsEven(value: Int): Boolean = false

  sealed trait Weapon

  case class Sword(length: Int) extends Weapon

  case object Staff extends Weapon

  case class Bow(arrows: Int) extends Weapon

  /* c) rock paper scissors ... with weapons:
   *    1. staff beats sword
   *    2. bow beats staff
   *    3. sword beats bow
   */
  def testWinsLeft(a: Weapon, b: Weapon): Weapon = Staff

  // d) return the number of arrows or -1 of it is not a bow
  def testNumOfArrows(weapon: Weapon): Int = -2
}
