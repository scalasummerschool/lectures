package exercise2

import org.scalacheck.Properties
import org.scalacheck.Arbitrary
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

object CompositionsSpec extends Properties("compositions") {

  implicit val eitherGen = Arbitrary[Either[String, Int]] {
    Gen.oneOf(true, false).flatMap { isRight =>
      if (isRight) Gen.posNum[Int].map(r => Right(r))
      else         Gen.alphaStr.map(l => Left(l))
    }
  }

  def map[A, B, C](e: Either[A, B])(f: B => C): Either[A, C] = e match {
    case Right(r) => Right(f(r))
    case Left(l)  => Left(l)
  }

  property("map either") = forAll { e: Either[String, Int] =>
    Compositions.testMap[String, Int, Boolean](e, _ % 2 == 0) == map(e)(_ % 2 == 0)
  }

  def flatMap[A, B, C](e: Either[A, B])(f: B => Either[A, C]): Either[A, C] = e match {
    case Right(r) => f(r)
    case Left(l)  => Left(l)
  }

  property("flatMap either") = forAll { e: Either[String, Int] =>
    Compositions.testFlatMap[String, Int, Boolean](e, r => Right(r % 2 == 0)) == flatMap(e)(r => Right(r % 2 == 0))
  }

  def double(a: Int): Either[String, Double]  = Right(a * 2.0)
  def show(d: Double): Either[String, String] = Right(d.toString)
  def prefix(s: String): String = "AWESOME " + s

  property("for-comprehension") = forAll { e: Either[String, Int] =>
    Compositions.testForComprehension(e)(double)(show)(prefix) == (
      for {
        v <- e
        d <- double(v)
        s <- show(d)
      } yield prefix(s)
    )
  }
}
