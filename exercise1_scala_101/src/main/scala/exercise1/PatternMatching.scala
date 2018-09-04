package exercise1

object PatternMatching {

  /* a) if value is:
   *    1 => "it is one"
   *    2 => "it is two"
   *    3 => "it is three"
   *    otherwise => "what's that"
   */



  def testIntToString(value: Int): String = value.toString

  /* b) `value` is true if it is:
   *    "max" or "Max
   *    "moritz" or "Moritz"
   */



  def testIsMaxAndMoritz(value: String): Boolean = false

  // c) is `value` even (use guards)



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



  def testDuell(a: Weapon, b: Weapon): Weapon = Staff

  // d) return the number of arrows or -1 of it is not a bow



  def testNumOfArrows(weapon: Weapon): Int = -2
}
