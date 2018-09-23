package exercise5

import cats.Functor

/** A Functor trait has the following abstract method:
  *   new Functor[List] {
  *     def map[A, B](fa: List[A])(f: A => B): List[B]
  *   }
  */
object Functors {

  // a) Implement implicit Functor[List] instance.


  def testListFunctor[A, B](fa: List[A], f: A => B): List[B] = Nil
}
