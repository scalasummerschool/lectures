package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object AreaSpecification extends Properties("area") {

  property("circle") = forAll { r: Double =>
    r * r * Math.PI == Area.circle(r)
  }

  property("rectangle") = forAll { (a: Double, b: Double) =>
    a * b == Area.rectangle(a)(b)
  }
}
