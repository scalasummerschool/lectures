package exercise5

import cats.instances.all.catsStdInstancesForList

/** Implement a Monad instance for ErrorOrT[_], A]. Use the kind-projector `?` when writing `new Monad[ErrorOrT[F, ?]]`.
  */

object MonadTransformers {

  type ErrorOr[A]        = Either[String, A]
  type ErrorOrT[F[_], A] = F[ErrorOr[A]]

  // apply your solution-typeclass from (a) here using cats syntax if possible, DO NOT change the signature
  def testOptionTransformer[A, B](fa: ErrorOrT[Option, A], f: A => ErrorOrT[Option, B]): ErrorOrT[Option, B] = None
}
