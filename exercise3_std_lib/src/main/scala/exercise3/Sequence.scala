package exercise3

import scala.annotation.tailrec

/** Write your solutions in the test functions.
  * 
  * Seq(1, 2) match {
  *   case head +: tail => ???
  *   case Nil          => ???
  *   case s            => ???
  * }
  * 
  * https://www.scala-lang.org/api/2.12.0/scala/collection/Seq.html
  */
// NOTE: write tail recursive functions
// NOTE: try to use the methods you have seen so far
object Sequence {

  // a) Find the last element of a Seq.
  def testLastElement[A](seq: Seq[A]): Option[A] = ???

  // b) Zip two Seqs (e.g. Seq(1, 2) and Seq(3, 4) become Seq((1, 3), (2, 4))) - when one Seq is longer ignore the remaining elements.
  def testZip[A](a: Seq[A], b: Seq[A]): Seq[(A, A)] = ???

  // c) Check if a condition holds for all elements in Seq.
  def testForAll[A](seq: Seq[A])(cond: A => Boolean): Boolean = false

  // d) Is Seq a palindrome, e.g. Seq(1, 2, 3, 2, 1)?
  def testPalindrom[A](seq: Seq[A]): Boolean = false

  // e) Implement flatMap with foldLeft.
  def testFlatMap[A, B](seq: Seq[A])(f: A => Seq[B]): Seq[B] = ???
}
