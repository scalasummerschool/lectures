package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object HigherOrderSpec extends Properties("area") {

  property("n-times (plus, multiply)") = forAll { (n: Int, a: Int, b: Int) =>
    n * HigherOrder.plus(a)(b) == HigherOrder.testNTimes(HigherOrder.plus, a, b, n) &&
    n * HigherOrder.multiply(a)(b) == HigherOrder.testNTimes(HigherOrder.multiply, a, b, n)
  }
}
