package exercise5

import cats.kernel._
import Comparison._

object KernelSolution {

  implicit val stringEq = new Eq[String] {

    def eqv(x: String, y: String): Boolean = x == y
  }

  def eqString(x: String, y: String): Boolean = Eq[String].eqv(x, y)


  implicit val intOrder = new Order[Int] {

    def compare(x: Int, y: Int): Int = (x - y).signum
  }

  def compareInt(x: Int, y: Int): Comparison = Order[Int].comparison(x, y)


  implicit def listSemi[A] = new Semigroup[List[A]] {

    def combine(x: List[A], y: List[A]): List[A] = x ++ y
  }

  def combineLists(x: List[Int], y: List[Int]): List[Int] = 
    Semigroup[List[Int]].combine(x, y)

  def combineListsArb[A](x: List[A], y: List[A]): List[A] = 
    Semigroup[List[A]].combine(x, y)
}
