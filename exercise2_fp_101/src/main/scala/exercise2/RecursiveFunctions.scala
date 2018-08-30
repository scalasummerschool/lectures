package exercise2

import scala.annotation.tailrec

// REMARK: try to make all functions tail recursive; use the annotation to proof it
object RecursiveFunctions {

  /* a) write a function that replicates a value n-times to create a list:
   *        def replicate[A](n: Int, a: A): List[A]
   */



  def testReplicate[A](n: Int, a: A): List[A] = Nil()

  /* c) write a function that reverses a list:
   *        def reverse[A](list: List[A]): List[A]
   */



  def testReverse[A](list: List[A]): List[A] = list

  /* b) write a function that applies a function to every value of a list:
   *        def map[A, B](list: List[A])(f: A => B): List[B]
   */



  def testMap[A, B](list: List[A], f: A => B): List[B] = Nil()
  
}
