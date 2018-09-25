package exercise5

import cats.kernel._
import Comparison._

/** Short type class summary:
  * 
  * trait Eq[A] {
  *   def eqv(x: A, y: A): Boolean = ???
  * }
  * 
  * trait Order[A] {
  *   def compare(x: A, y: A): Int = ???
  * }
  * 
  * trait Semigroup[A] {
  *   def combine(x: A, y: A): A = ???
  * }
  */
object Kernel {

  // a) Implement an implicit Eq[String] instance.


  def testEqString(x: String, y: String): Boolean = false

  // b) Implement an implicit Order[Int] instance.


  def testCompareInt(x: Int, y: Int): Comparison = GreaterThan

  // c) Implement an implicit Semigroup[List[Int]] instance.


  def testCombineLists(x: List[Int], y: List[Int]): List[Int] = x

  // d) Update your List Semigroup such that it works for any element type, e.g. List[Int], List[String], ...


  def testCombineListsArb[A](x: List[A], y: List[A]): List[A] = x


  // e) Can you find a type class in kernel which adds an empty element to Semigroup? How is it called?
}
