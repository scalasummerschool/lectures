package exercise5

import cats.Applicative

/** An Applicative trait has the following abstract methods:
  *   new Applicative[List] {
  *     def pure[A](a: A): List[A] = ???
  * 
  *     def ap[A, B](ff: List[A => B])(fa: List[A]): List[B] = ???
  *   }
  */
object Applicatives {

  // a) Implement implicit Applicative[List] instance.


  def testListApplicative[A, B](ff: List[A => B], fa: List[A]): List[B] = Nil

  // b) Apply all parameters to the function.

  def testApplyApp(f: Int => Int => String, x: List[Int], y: List[Int]): List[String] = Nil
}
