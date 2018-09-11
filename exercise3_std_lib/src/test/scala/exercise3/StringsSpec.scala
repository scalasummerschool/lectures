package exercise3

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

object StringsSpec extends Properties("String") {

  property("uppercase") = forAll { str: String =>
    Strings.testUppercase(str) == StringsSolution.uppercase(str)
  }

  property("interpolation 1") = forAll { (name: String, age: Int) =>
    Strings.testInterpolations(name, age) == StringsSolution.interpolations(name, age)
  }

  property("interpolation 2 (computation)") = forAll { (a: Int, b: Int) =>
    Strings.testComputation(a, b) == StringsSolution.computation(a, b)
  }

  property("take two") = forAll { str: String =>
    Strings.testTakeTwo(str) == StringsSolution.takeTwo(str)
  }
}
