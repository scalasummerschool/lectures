package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object FunctionsSpec extends Properties("functions") {

  property("circle") = forAll { r: Double =>
    r * r * Math.PI == Area.testCircle(r)
  }

  property("rectangle") = forAll { (a: Double, b: Double) =>
    a * b == Area.testRectangle(a, b)
  }

  property("rectangle uncurried") = forAll { (a: Double, b: Double) =>
    a * b == Area.testRectangleUc(a, b)
  }

  property("rectangle unscore") = forAll { (a: Double, b: Double) =>
    a * b == Area.testRectangleUs(a, b)
  }
}
