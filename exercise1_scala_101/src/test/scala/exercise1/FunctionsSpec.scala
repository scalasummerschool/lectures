package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object FunctionsSpec extends Properties("functions") {

  property("circle") = forAll { r: Double =>
    r * r * Math.PI == Functions.testCircle(r)
  }

  property("rectangle") = forAll { (a: Double, b: Double) =>
    a * b == Functions.testRectangle(a, b)
  }

  property("rectangle uncurried") = forAll { (a: Double, b: Double) =>
    a * b == Functions.testRectangleUc(a, b)
  }

  property("rectangle unscore") = forAll { (a: Double, b: Double) =>
    a * b == Functions.testRectangleUs(a, b)
  }
}
