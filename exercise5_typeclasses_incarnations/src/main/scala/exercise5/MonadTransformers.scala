package exercise5

import cats.instances.all.catsStdInstancesForList

/** Implement a Monad instance for OptionT[F[_], A]. Use the kind-projector `?` when writing `new Monad[OptionT[F, ?]]`.
  */

object MonadTransformers {

  type OptionT[F[_], A] = F[Option[A]]

  def testOptionTransformer[A, B](fa: OptionT[List, A], f: A => OptionT[List, B]): OptionT[List, B] = Nil
}
