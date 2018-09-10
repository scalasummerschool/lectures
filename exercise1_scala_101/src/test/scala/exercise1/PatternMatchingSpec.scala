package exercise1

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll

object PatternMatchingSpec extends Properties("pattern matching") {

  import PatternMatching._

  def intToString(value: Int): String = value match {
    case 1 => "it is one"
    case 2 => "it is two"
    case 3 => "it is three"
    case _ => "what's that"
  }

  property("Ints to Strings") = forAll(Gen.oneOf(1, 2, 3, 4)) { value =>
    intToString(value) == testIntToString(value)
  }

  def isMaxOrMoritz(kid: String): Boolean = kid match {
    case "Max" | "max"       => true
    case "Moritz" | "moritz" => true
    case _                   => false
  }

  property("is it Max or Moritz") = forAll(Gen.oneOf("Max", "max", "Moritz", "moritz", "Joe", "Jim", "mAx")) { value =>
     isMaxOrMoritz(value) == testIsMaxAndMoritz(value)
  }

  def isEven(value: Int): Boolean = value match {
    case _ if value % 2 == 0 => true
    case _                   => false
  }

  property("is a number even") = forAll { value: Int =>
    isEven(value) == testIsEven(value)
  }

  def playerAWins(a: Hand, b: Hand): Result = (a, b) match {
    case (Rock, Scissor)      => Win
    case (Scissor, Paper)     => Win
    case (Paper, Rock)        => Win
    case (ha, hb) if ha == hb => Draw
    case _                    => Lose
  }

  implicit val handGen = Arbitrary[Hand](Gen.oneOf(Rock, Paper, Scissor))

  property("rock paper scissors, does player a win") = forAll { (ha: Hand, hb: Hand) =>
    testWinsA(ha, hb) == playerAWins(ha, hb)
  }

  implicit val animalGen: Arbitrary[Animal] = Arbitrary(for {
    name <- Gen.oneOf("cat", "parrot", "goldfish")
  } yield name match {
    case "cat"      => Mammal(name, Meat)
    case "parrot"   => Bird(name, Vegetables)
    case "goldfish" => Fish(name, Plants)
  })

  def extractName(animal: Animal): String = animal match {
    case Mammal(name, _) => name
    case _               => "NO MAMMAL"
  }

  property("extract mammal name") = forAll { animal: Animal =>
    testExtractMammalName(animal) == extractName(animal)
  }

  def updateFood(animal: Animal): Animal = animal match {
    case Bird(name, _)  => Bird(name, Plants)
    case f @ Fish(_, _) => f
    case _              => animal
  }

  property("update food") = forAll { animal: Animal =>
    testUpdateFood(animal) == updateFood(animal)
  }
}
