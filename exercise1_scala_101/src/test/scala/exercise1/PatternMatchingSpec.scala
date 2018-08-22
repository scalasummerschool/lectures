package exercise1

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

object PatternMatchingSpec extends Properties("pattern matching") {

  property("match strings") = forAll { value: String =>
    PatternMatching.testLongString(value) == (if (value.length > 10) value else "String too short")
  }

  property("match ints") = forAll { value: Int =>
    PatternMatching.testIsEven(value) == (value % 2 == 0)
  }

  val furniteGen = for {
    numOfPeople <- Gen.posNum[Int]
    height      <- Gen.posNum[Int]
    caseIs      <- Gen.oneOf("bed", "chair", "ladder")
  } yield caseIs match {
    case "bed"    => PatternMatching.Bed(numOfPeople)
    case "chair"  => PatternMatching.Chair
    case "ladder" => PatternMatching.Ladder(height)
  }

  property("match Furniture") = forAll(furniteGen) { furniture =>
    PatternMatching.testFurniture(furniture) == (furniture match {
      case PatternMatching.Bed(_)    => "sleep"
      case PatternMatching.Chair     => "sit"
      case PatternMatching.Ladder(_) => "stand"
    })
  }

  property("number of people in a bed") = forAll(furniteGen) { furniture =>
    PatternMatching.testNumPeopleInBed(furniture) == (furniture match {
      case PatternMatching.Bed(people) => people
      case _                           => -1
    })
  }
}
