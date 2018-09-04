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

  implicit val weaponGen: Arbitrary[Weapon] = Arbitrary(for {
    length <- Gen.posNum[Int]
    arrows <- Gen.posNum[Int]
    weapon <- Gen.oneOf("sword", "staff", "bow")
  } yield weapon match {
    case "sword" => Sword(length)
    case "staff" => Staff
    case "bow"   => Bow(arrows)
  })

  def duell(a: Weapon, b: Weapon): Weapon = (a, b) match {
    case (Staff, Sword(_))  => a
    case (Staff, _)         => b
    case (Bow(_), Staff)    => a
    case (Bow(_), _)        => b
    case (Sword(_), Bow(_)) => a
    case (Sword(_), _)      => b
  }

  property("duell") = forAll { (a: Weapon, b: Weapon) =>
    duell(a, b) == testDuell(a, b)
  }

  def numOfArrows(weapon: Weapon): Int = weapon match {
    case Bow(arrows) => arrows
    case _           => -1
  }

  property("number of arrows") = forAll { weapon: Weapon =>
    numOfArrows(weapon) == testNumOfArrows(weapon)
  }
}
