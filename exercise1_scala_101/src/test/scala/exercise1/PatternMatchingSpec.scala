package exercise1

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll

object PatternMatchingSpec extends Properties("pattern matching") {

  import PatternMatching._

  property("match strings") = forAll { value: String =>
    testLongString(value) == (if (value.length > 10) value else "String too short")
  }

  property("match ints") = forAll { value: Int =>
    testIsEven(value) == (value % 2 == 0)
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

  property("wins left the weapons fight") = forAll { (a: Weapon, b: Weapon) =>
    testWinsLeft(a, b) == ((a, b) match {
      case (Staff, Sword(_))  => a
      case (Staff, _)         => b
      case (Bow(_), Staff)    => a
      case (Bow(_), _)        => b
      case (Sword(_), Bow(_)) => a
      case (Sword(_), _)      => b
    })
  }

  property("number of arrows") = forAll { weapon: Weapon =>
    testNumOfArrows(weapon) == (weapon match {
      case Bow(arrows) => arrows
      case _                           => -1
    })
  }
}
