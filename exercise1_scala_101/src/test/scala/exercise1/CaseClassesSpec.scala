package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object CaseClassesSpec extends Properties("case classes") {

  property("calculate age") = forAll { (birthyear: Int, year: Int) =>
    CaseClasses.testAge(birthyear, year) == year - birthyear
  }

  property("generate full name") = forAll { (firstName: String, lastName: String) =>
    CaseClasses.testFullname(firstName, lastName) == s"$firstName $lastName"
  }

  property("move to new address") = forAll { (street: String, city: String, country: String) =>
    CaseClasses.testMove(street, city, country)
  }
}
