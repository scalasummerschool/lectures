package exercise5

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object FunctorsSpec extends Properties("Functor") {

  property("List Functor") = forAll { fa: List[Int] =>
    val f: Int => Int = _ * 1

    FunctorsSolution.listFunctor(fa, f) == Functors.testListFunctor(fa, f)
  }
}
