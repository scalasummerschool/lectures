package exercise5

import cats.kernel._
import cats.implicits._
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

  // a) Test equality using Eq[_].

  // apply your solution-typeclass from (a) here using cats syntax if possible, DO NOT change the signature
  def testEqString(x: String, y: String): Boolean = false

  // b) Test order using Order[_].

  // apply your solution-typeclass from (b) here using cats syntax if possible, DO NOT change the signature
  def testCompareInt(x: Int, y: Int): Comparison = GreaterThan

  // c) Combine two list using Semigroup[_].

  // apply your solution-typeclass from (c) here using cats syntax if possible, DO NOT change the signature
  def testCombineLists(x: List[Int], y: List[Int]): List[Int] = x


  // e) Can you find a type class in kernel which adds an empty element to Semigroup? How is it called?
}
