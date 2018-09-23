package exercise5

import cats.Functor

import scala.annotation.tailrec

object FunctorsSolution {

  implicit val listFunct = new Functor[List] {

    def map[A, B](fa: List[A])(f: A => B): List[B] = {
      @tailrec
      def loop(rem: List[A], agg: List[B]): List[B] = rem match {
        case h :: tail => loop(tail, f(h) :: agg)
        case Nil       => agg.reverse
      }

      loop(fa, Nil)
    }
  }

  def listFunctor[A, B](fa: List[A], f: A => B): List[B] = Functor[List].map(fa)(f)
}
