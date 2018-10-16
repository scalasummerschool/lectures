package exercise5

import cats._

/** The following code snippet show you which type-class functions you have to implement:
  * 
  *   new Functor[ErrorOr] {
  *     def map[A, B](fa: ErrorOr[A])(f: A => B): ErrorOr[B]
  *   }
  * 
  * 
  *   new Applicative[ErrorOr] {
  *     def pure[A](a: A): ErrorOr[A] = ???
  * 
  *     def ap[A, B](ff: ErrorOr[A => B])(fa: ErrorOr[A]): ErrorOr[B] = ???
  *   }
  * 
  *   new Monad[ErrorOr] {
  *     def pure[A](a: A): ErrorOr[A] = ???
  * 
  *     def flatMap[A, B](fa: ErrorOr[A])(f: A => ErrorOr[B]): ErrorOr[B] = ???
  * 
  *     // here you have to reduce `f(a)` to `ErrorOr[B]` by ignoring `Left` values
  *     def tailRecM[A, B](a: A)(f: A => ErrorOr[Either[A,B]]): ErrorOr[B] = ???
  *   }
  */
object Core {

  type ErrorOr[A] = Either[String, A]

  // a) Implement implicit Functor[ErrorOr] instance.




  // apply your solution-typeclass from (a) here using cats syntax if possible, DO NOT change the signature
  def testErrorOrFunctor[A, B](fa: ErrorOr[A], f: A => B): ErrorOr[B] = Left("wrong")

  // b) Implement an implicit Applicative[ErrorOr] instance.




  // apply your solution-typeclass from (b) here using cats syntax if possible, DO NOT change the signature
  def testErrorOrApplicative[A, B](ff: ErrorOr[A => B], fa: ErrorOr[A]): ErrorOr[B] = Left("wrong")

  // c) Apply all parameters to the function.

  // apply your solution-typeclass from (c) here using cats syntax if possible, DO NOT change the signature
  def testApplyApp(f: Int => Int => String, x: ErrorOr[Int], y: ErrorOr[Int]): ErrorOr[String] = {
    import cats.syntax.all._

    Left("wrong")
  }

  // d) Implement an implicit Monad[ErrorOr] instance.




  def testErrorOrMonad[A, B](fa: ErrorOr[A], f: A => ErrorOr[B]): ErrorOr[B] = Left("wrong")

}
