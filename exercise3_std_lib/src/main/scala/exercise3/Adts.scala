package exercise3

import scala.util.{Try, Failure}

object Adts {

  // a) given a List[Int] return the nth number



  def testGetNth(list: List[Int], n: Int): Option[Int] = None

  // b) double the given number or return 0 if not available
  


  def testDoubleOrZero(n: Option[Int]): Int = -1

  // c) check if a given Int is even. If so, return it as a Right. Otherwise, return Left("Not an even number.")



  def testIsEven(n: Int): Either[String, Int] = Left("meh")

  // d) safe division for Integers. Return Right with the result or Left("You cannot divide by zero.")



  def testSafeDivide(a: Int, b: Int): Either[String, Int] = Left("meh")

  // e) given a impure function, handle Exceptions, recover from them by returning 0



  def testGoodOldJava(impure: String => Int, str: String): Try[Int] = Failure(new IllegalArgumentException("meh"))

}
