package exercise1

object PatternMatching {

  // a) if `value` is longer than 10 signs return it else return "String too short"
  def testLongString(value: String): String = value

  // b) if `value` is even return true else false
  def testIsEven(value: Int): Boolean = false

  // c) return the expected usage for the furniture
  sealed trait Furniture

  // sleep
  case class Bed(numOfPeople: Int) extends Furniture
  // sit
  case object Chair extends Furniture
  // stand
  case class Ladder(height: Int) extends Furniture

  def testFurniture(furn: Furniture): String = "???"

  // d) return the number of people for a bed else -1
  def testNumPeopleInBed(furn: Furniture): Int = -2
}
