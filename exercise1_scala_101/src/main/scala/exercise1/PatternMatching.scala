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


  

  /* c) rock paper scissors (see classes below):
   *    1. rock beats scissor
   *    2. scissor beats paper
   *    3. paper beats rock
   *    Does player a win?
   */



  def testWinsA(a: Hand, b: Hand): Result = Draw

  // use the definition of Animals below

  // d) Extract the name of a Mammal or return "NO MAMMAL".



  def testExtractMammalName(animal: Animal): String = animal.name

  // e) The the food for Fishes and Birds to Plants, leaf the Mammals as they are



  def testUpdateFood(animal: Animal): Animal = animal





  sealed trait Hand
  case object Rock    extends Hand
  case object Paper   extends Hand
  case object Scissor extends Hand

  sealed trait Result
  case object Win  extends Result
  case object Lose extends Result
  case object Draw extends Result

  sealed trait Food
  case object Meat       extends Food
  case object Vegetables extends Food
  case object Plants     extends Food

  sealed trait Animal {

    val name: String
    val food: Food
  }
  case class Mammal(name: String, food: Food) extends Animal
  case class Fish(name: String, food: Food)   extends Animal
  case class Bird(name: String, food: Food)   extends Animal
}
