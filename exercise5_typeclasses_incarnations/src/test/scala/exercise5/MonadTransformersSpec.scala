package exercise5

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object MonadTransformerssSpec extends Properties("Monad Transformer") {

  import MonadTransformers.ErrorOrT

  property("Option[ErrorOf] Monad") = forAll { fa: ErrorOrT[Option, Int] =>
    val f: Int => ErrorOrT[Option, Int] = a => Some(Right(a * 2))

    MonadTransformersSolution.optionTransformer(fa, f) == MonadTransformers.testOptionTransformer(fa, f)
  }
}
