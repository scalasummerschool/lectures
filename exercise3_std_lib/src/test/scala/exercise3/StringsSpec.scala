package exercise3

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

import scala.annotation.tailrec

object StringsSpec extends Properties("String") {

  def uppercase(str: String): String = str.map(_.toUpper)

  property("uppercase") = forAll { str: String =>
    Strings.testUppercase(str) == uppercase(str)
  }

  def interpolations(name: String, age: Int) = s"Hi my name is $name and I am $age years old."

  property("interpolation 1") = forAll { (name: String, age: Int) =>
    Strings.testInterpolations(name, age) == interpolations(name, age)
  }

  def computation(a: Int, b: Int): String =
    """
      |Hi,
      |now follows a quite hard calculation. We try to add:
      |  a := $a
      |  b := $b
      |
      |  result is ${a + b}
    """.stripMargin

  property("interpolation 2 (computation)") = forAll { (a: Int, b: Int) =>
    Strings.testComputation(a, b) == computation(a, b)
  }

  def takeTwo(str: String): String = 
    if (str.length >= 2) str.take(2)
    else                 str

  property("take two") = forAll { str: String =>
    Strings.testTakeTwo(str) == takeTwo(str)
  }
}
