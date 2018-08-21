package exercise1

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object TraitsSpec extends Properties("traits") {

  def show(value: Int): String = {
    val str   = value.toString
    val euro  = str.dropRight(2)
    val cents = str.takeRight(2)

    s"$euro.$cents EUR"
  }

  property("show Euro") = forAll { value: Int =>
    Traits.testShowEuro(value) == show(value)
  }

  property("add Euros") = forAll { (a: Int, b: Int) =>
    Traits.testPlusEuro(a, b) == show(a + b)
  }
}
