package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object HigherOrderSpec extends Properties("higher-order") {

  property("n-times (plus, multiply)") = forAll { (n: Int, a: Int, b: Int) =>
    HigherOrderSolution.nTimes(HigherOrder.plus, a, b, n)     == HigherOrder.testNTimes(HigherOrder.plus, a, b, n) &&
    HigherOrderSolution.nTimes(HigherOrder.multiply, a, b, n) == HigherOrder.testNTimes(HigherOrder.multiply, a, b, n)
  }

  property("anonymous function") = forAll { (n: Int, a: Int, b: Int) =>
     (if (a > b) a else b) * n == HigherOrder.testAnonymousNTimes(a, b, n)
  }
}
