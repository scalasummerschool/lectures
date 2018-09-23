package exercise5

import cats.Monad

import scala.annotation.tailrec

object MonadsSolution {

  implicit val listMon = new Monad[List] {

    def pure[A](a: A): List[A] = List(a)

    def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] = {
      @tailrec
      def loop(rem: List[A], agg: List[B]): List[B] = rem match {
        case h :: tail => loop(tail, f(h) ++ agg)
        case Nil       => agg.reverse
      }

      loop(fa, Nil)
    }

    def tailRecM[A, B](a: A)(f: A => List[Either[A,B]]): List[B] = {
      @tailrec
      def loop(rem: List[Either[A, B]], agg: List[B]): List[B] = rem match {
        case Right(b) :: tail => loop(tail, b :: agg)
        case Left(_) :: tail  => loop(tail, agg)
        case Nil              => agg.reverse
      }

      loop(f(a), Nil)
    }
  }

  def listMonad[A, B](fa: List[A], f: A => List[B]): List[B] = Monad[List].flatMap(fa)(f)
}
