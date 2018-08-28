package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object ClassesSpec extends Properties("classes") {

  property("calculate age") = forAll { (birthyear: Int, year: Int) =>
    Classes.testAge(birthyear, year) == year - birthyear
  }

  property("generate full name") = forAll { (firstName: String, lastName: String) =>
    Classes.testFullname(firstName, lastName) == s"$firstName $lastName"
  }

  property("move to new address") = forAll { (street: String, city: String, country: String) =>
    Classes.testMove(street, city, country)
  }
}
