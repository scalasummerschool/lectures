package exercise5

import cats.Monad

/** A Monad trait has the following abstract methods:
  *   new Monad[List] {
  *     def pure[A](a: A): List[A] = ???
  * 
  *     def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] = ???
  * 
  *     // here you have to reduce `f(a)` to `List[B]` by ignoring `Left` values
  *     def tailRecM[A, B](a: A)(f: A => List[Either[A,B]]): List[B] = ???
  *   }
  */
object Monads {

  // a) Implement an implicit Monad[List] instance.


  def testListMonad[A, B](fa: List[A], f: A => List[B]): List[B] = Nil
}
