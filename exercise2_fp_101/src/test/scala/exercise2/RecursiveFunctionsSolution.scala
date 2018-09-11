package exercise2

import scala.annotation.tailrec

object RecursiveFunctionsSolution {

  def replicate[A](n: Int, a: A): List[A] = {
    @tailrec
      def loop(agg: List[A], rem: Int): List[A] = {
        if (rem == 0) agg
        else          loop(Cons(a, agg), rem - 1)
      }

    loop(Nil(), n)
  }

  def reverse[A](list: List[A]): List[A] = {
    @tailrec
    def loop(rem: List[A], agg: List[A]): List[A] = rem match {
      case Nil()         => agg
      case Cons(a, tail) => loop(tail, Cons(a, agg))
    }

    loop(list, Nil())
  }

  def map[A, B](list: List[A])(f: A => B): List[B] = {
    @tailrec
    def loop(rem: List[A], agg: List[B]): List[B] = rem match {
      case Nil()         => agg
      case Cons(a, tail) => loop(tail, Cons(f(a), agg))
    }

    reverse(loop(list, Nil()))
  }

  def combine[A](l: List[A], r: List[A]): List[A] = {
    @tailrec
    def loop(lReversedRem: List[A], agg: List[A]): List[A] = lReversedRem match {
      case Nil()         => agg
      case Cons(a, tail) => loop(tail, Cons(a, agg))
    }

    loop(reverse(l), r)
  }

  def take[A](n: Int, as: List[A]): List[A] = {
    if (n < 0)
      Nil()
    else if (n >= RecursiveFunctions.length(as))
      as
    else {
      @tailrec
      def loop(rem: List[A], agg: List[A]): List[A] = rem match {
        case Nil()         => Nil()
        case Cons(a, tail) => 
          val updatedAgg = Cons(a, agg)

          if (RecursiveFunctions.length(updatedAgg) == n) 
            reverse(updatedAgg)
          else
            loop(tail, updatedAgg)
      }

      loop(as, Nil())
    }
  }
}
