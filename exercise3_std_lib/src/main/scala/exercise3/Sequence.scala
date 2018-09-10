package exercise3

import scala.annotation.tailrec

// NOTE: write recursive functions tail recursive
// NOTE: try to use the methods you have seen so far
object Sequence {

  // a) find the last element of a Seq
  def testLastElement[A](seq: Seq[A]): Option[A] = ???

  // b) reverse a Seq - DO NOT use the reverse method
  def testReverse[A](seq: Seq[A]): Seq[A] = seq

  // c) zip two Seq (Seq(1, 2) and Seq(3, 4) => Seq((1, 3), (2, 4))) - when one Seq is longer ignore remaining elements
  def testZip[A](a: Seq[A], b: Seq[A]): Seq[(A, A)] = ???

  // d) check if a condition holds for all element in Seq
  def testForAll[A](seq: Seq[A])(cond: A => Boolean): Boolean = false

  // e) is Seq a palindrom, e.g. Seq(1, 2, 3, 2, 1)
  def testPalindrom[A](seq: Seq[A]): Boolean = false

  // f) deduplicate a Seq - DO NOT use the distinct method and keep the elements in order
  def testDeduplicate[A](seq: Seq[A]): Seq[A] = seq

  // g) implement flatMap with foldLeft
  def testFlatMap[A, B](seq: Seq[A])(f: A => Seq[B]): Seq[B] = ???
}
