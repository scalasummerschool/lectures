package exercise1

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import org.scalacheck.Prop._

object PatternMatchingSpec extends Properties("pattern matching") {

  import PatternMatching._

  property("Ints to Strings") = forAll(Gen.oneOf(1, 2, 3, 4)) { value =>
    PatternMatchingSolution.intToString(value) ?= testIntToString(value)
  }

  property("is it Max or Moritz") = forAll(Gen.oneOf("Max", "max", "Moritz", "moritz", "Joe", "Jim", "mAx")) { value =>
    PatternMatchingSolution.isMaxOrMoritz(value) ?= testIsMaxAndMoritz(value)
  }

  property("is a number even") = forAll { value: Int =>
    PatternMatchingSolution.isEven(value) ?= testIsEven(value)
  }

  implicit val handGen = Arbitrary[Hand](Gen.oneOf(Rock, Paper, Scissor))

  property("rock paper scissors, does player a win") = forAll { (ha: Hand, hb: Hand) =>
    PatternMatchingSolution.playerAWins(ha, hb) ?= testWinsA(ha, hb)
  }

  implicit val animalGen: Arbitrary[Animal] = Arbitrary(for {
    name   <- Gen.oneOf("cat", "parrot", "goldfish")
    weight <- Gen.posNum[Int]
  } yield name match {
    case "cat"      => Mammal(name, Meat, weight)
    case "parrot"   => Bird(name, Vegetables)
    case "goldfish" => Fish(name, Plants)
  })

  property("extract mammal weight") = forAll { animal: Animal =>
    PatternMatchingSolution.extractWeight(animal) ?= testExtractMammalWeight(animal)
  }

  property("update food") = forAll { animal: Animal =>
    PatternMatchingSolution.updateFood(animal) ?= testUpdateFood(animal)
  }
}
