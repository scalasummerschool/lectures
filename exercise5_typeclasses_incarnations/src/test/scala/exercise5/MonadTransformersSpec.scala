package exercise5

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object MonadTransformerssSpec extends Properties("Monad Transformer") {

  property("List[Option] Monad") = forAll { fa: List[Option[Int]] =>
    val f: Int => List[Option[Int]] = a => List(Some(a * 1), None, Some(a + 1))

    MonadTransformersSolution.optionTransformer(fa, f) == MonadTransformers.testOptionTransformer(fa, f)
  }
}
