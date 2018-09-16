package exercise2

import org.scalacheck.Properties
import org.scalacheck.Arbitrary
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

object CompositionsSpec extends Properties("compositions") {

  implicit val optionGen = Arbitrary[Option[Int]] {
    Gen.oneOf(true, false).flatMap { isSome =>
      if (isSome) Gen.posNum[Int].map(r => Some(r))
      else        Gen.posNum[Int].map(_ => None())
    }
  }

  def double(a: Int): Double  = a * 2.0
  def show(d: Double): String = d.toString
  def prefix(s: String): String = "AWESOME " + s

  property("composition") = forAll { i: Int =>
    Compositions.testCompose(double)(show)(prefix)(i) == CompositionsSolution.compose(double)(show)(prefix)(i)
  }

  def doubleOpt(a: Int): Option[Double]  = Some(a * 2.0)
  def showOpt(d: Double): Option[String] = Some(d.toString)

  property("map and flatMap") = forAll { opt: Option[Int] =>
    Compositions.testMapFlatMap(doubleOpt)(showOpt)(prefix)(opt) == CompositionsSolution.mapFlatMap(doubleOpt)(showOpt)(prefix)(opt)
  }

  property("for-comprehension") = forAll { opt: Option[Int] =>
    Compositions.testForComprehension(doubleOpt)(showOpt)(prefix)(opt) == CompositionsSolution.forComprehension(doubleOpt)(showOpt)(prefix)(opt)
  }
}
