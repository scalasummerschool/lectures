package exercise2

import scala.annotation.tailrec

object RecursiveFunctionsSolution {

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

  def append[A](l: List[A], r: List[A]): List[A] = {
    @tailrec
    def loop(lReversedRem: List[A], agg: List[A]): List[A] = lReversedRem match {
      case Nil()         => agg
      case Cons(a, tail) => loop(tail, Cons(a, agg))
    }

    loop(reverse(l), r)
  }

  def flatMap[A, B](list: List[A])(f: A => List[B]): List[B] = {
    @tailrec
    def loop(rem: List[A], agg: List[B]): List[B] = rem match {
      case Nil()         => agg
      case Cons(a, tail) => loop(tail, append(f(a), agg))
    }

    reverse(loop(list, Nil()))
  }
}
