package exercise5

import cats.kernel._
import cats.implicits._
import Comparison._

object KernelSolution {

  def eqString(x: String, y: String): Boolean = x === y

  def compareInt(x: Int, y: Int): Comparison = Order[Int].comparison(x, y)

  def combineLists(x: List[Int], y: List[Int]): List[Int] = x |+| y
}
