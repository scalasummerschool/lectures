package exercise5

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object MonadsSpec extends Properties("Monad") {

  property("List Monad") = forAll { fa: List[Int] =>
    val f: Int => List[Int] = a => List(a * 1, a + 1)

    MonadsSolution.listMonad(fa, f) == Monads.testListMonad(fa, f)
  }
}
