package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object FunctionsSpec extends Properties("functions") {

  def circle(r: Double): Double = r * r * Math.PI

  property("circle") = forAll { r: Double =>
    circle(r) == Functions.testCircle(r)
  }

  def rectangleCurried(a: Double)(b: Double): Double = a * b

  property("rectangle curried") = forAll { (a: Double, b: Double) =>
    rectangleCurried(a)(b) == Functions.testRectangleCurried(a, b)
  }

  def rectangleUncurried(a: Double, b: Double): Double = rectangleCurried(a)(b)

  property("rectangle uncurried") = forAll { (a: Double, b: Double) =>
    rectangleUncurried(a, b) == Functions.testRectangleUc(a, b)
  }

  val rectangleUnderscore: (Double, Double) => Double = _ * _

  property("rectangle unscore") = forAll { (a: Double, b: Double) =>
    rectangleUnderscore(a, b) == Functions.testRectangleUs(a, b)
  }
}
