package exercise3

import org.scalacheck.Properties
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll

import scala.util.Try

object AdtsSpec extends Properties("ADT") {

  def getNth(list: List[Int], n: Int): Option[Int] = 
    if (list.length >= n) 
      None
    else
      list.drop(n).headOption

  property("get nth element") = forAll(listGen) { case (list, n) =>
    Adts.testGetNth(list, n) == getNth(list, n)
  }

  def doubleOrZero(n: Option[Int]): Int = n.fold(0)(_ * 2)

  property("double optional number or zero") = forAll { n: Option[Int] =>
    Adts.testDoubleOrZero(n) == doubleOrZero(n)
  }

  def isEven(n: Int): Either[String, Int] =
    if (n % 2 == 0) Right(n)
    else            Left("Not an even number.")

  property("is an even number") = forAll { n: Int =>
    Adts.testIsEven(n) == isEven(n)
  }

  def safeDivide(a: Int, b: Int): Either[String, Int] = 
    if (b == 0) Left("You cannot divide by zero.")
    else        Right(a / b)

  property("safe divide") = forAll { (a: Int, b: Int) =>
    Adts.testSafeDivide(a, b) == safeDivide(a, b)
  }

  def goodOldJava(impure: String => Int, str: String): Try[Int] =
    Try(impure(str)).recover {
      case _ => 0
    }

  property("good old unsafe code") = forAll { str: String =>
    def javaStyle(str: String): Int = {
      if (str.length > 20) throw new IllegalArgumentException("nope")
      else                 str.length
    }

    Adts.testGoodOldJava(javaStyle, str) == goodOldJava(javaStyle, str)
  }

  val listGen = for {
    list      <- Gen.listOfN(10, Gen.choose(0, 1000))
    n         <- Gen.choose(0, 100)
    nTooLarge <- Gen.oneOf(true, false)
  } yield {
    if (nTooLarge) 
      (list, list.length + n)
    else if (n >= list.length)
      (list, list.length - 1)
    else
      (list, n)
  }
}
