package exercise3

import org.scalacheck.Properties
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop._

object AdtsSpec extends Properties("ADT") {

  property("get nth element") = forAll(listGen) { case (list, n) =>
    Adts.testGetNth(list, n) =? AdtsSolution.getNth(list, n)
  }

  property("double optional number") = forAll { n: Option[Int] =>
    Adts.testDouble(n) =? AdtsSolution.double(n)
  }

  property("is an even number") = forAll { n: Int =>
    Adts.testIsEven(n) =? AdtsSolution.isEven(n)
  }

  property("safe divide") = forAll { (a: Int, b: Int) =>
    Adts.testSafeDivide(a, b) =? AdtsSolution.safeDivide(a, b)
  }

  property("good old unsafe code") = forAll { str: String =>
    def javaStyle(str: String): Int = {
      if (str.length > 20) throw new IllegalArgumentException("nope")
      else                 str.length
    }

    Adts.testGoodOldJava(javaStyle, str) =? AdtsSolution.goodOldJava(javaStyle, str)
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
