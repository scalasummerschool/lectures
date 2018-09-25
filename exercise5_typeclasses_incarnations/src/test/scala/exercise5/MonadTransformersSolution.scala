package exercise5

import cats.Monad
import cats.instances.all.catsStdInstancesForList

object MonadTransformersSolution {

  import MonadTransformers.OptionT

  implicit def optionT[F[_]: Monad] = new Monad[OptionT[F, ?]] {
  
    def pure[A](a: A): OptionT[F, A] = Monad[F].pure(Some(a))
  
    def flatMap[A, B](fa: OptionT[F, A])(f: A => OptionT[F, B]): OptionT[F, B] = 
      Monad[F].flatMap(fa) {
        case Some(v) => f(v)
        case None    => Monad[F].pure(None)
      }

    def tailRecM[A, B](a: A)(f: A => OptionT[F, Either[A, B]]): OptionT[F, B] = Monad[F].map(f(a)) {
      case Some(Right(b)) => Some(b)
      case _              => None
    }
  }

  def optionTransformer[A, B](fa: OptionT[List, A], f: A => OptionT[List, B]): OptionT[List, B] = 
    Monad[OptionT[List, ?]].flatMap(fa)(f)
}
