package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object FunctionsSpec extends Properties("functions") {

  property("circle") = forAll { r: Double =>
    FunctionsSolution.circle(r) == Functions.testCircle(r)
  }

  property("rectangle curried") = forAll { (a: Double, b: Double) =>
    FunctionsSolution.rectangleCurried(a)(b) == Functions.testRectangleCurried(a, b)
  }

  property("rectangle uncurried") = forAll { (a: Double, b: Double) =>
    FunctionsSolution.rectangleUncurried(a, b) == Functions.testRectangleUc(a, b)
  }
}
