package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object PersonSpec extends Properties("person") {

  property("calculate age") = forAll { (birthyear: Int, year: Int) =>
    PersonTests.testAge(birthyear, year) == year - birthyear
  }

  property("generate full name") = forAll { (firstName: String, lastName: String) =>
    PersonTests.testFullname(firstName, lastName) == s"$firstName $lastName"
  }

  property("move to new address") = forAll { (street: String, city: String, country: String) =>
    PersonTests.testMove(street, city, country)
  }
}
