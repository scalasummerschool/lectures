package exercise2

import scala.annotation.tailrec

/** Implement stand-alone functions for your solutions.
  * REMARK: Try to make all functions tail recursive; use the annotation to proof it.
  * 
  * A function is tail recursive if if:
  *   1. it is single direct recursion
  *   2. the last expression is the recursive call
  */
object RecursiveFunctions {

  def length[A](as: List[A]): Int = {
    @tailrec
    def loop(rem: List[A], agg: Int): Int = rem match {
      case Cons(_, tail) => loop(tail, agg + 1)
      case Nil()         => agg
    }

    loop(as, 0)
  }

  /* a) Write a function that reverses a list:
   *        def reverse[A](list: List[A]): List[A]
   */



  def testReverse[A](list: List[A]): List[A] = list

  /* b) Write a function that applies a function to every value of a list:
   *        def map[A, B](list: List[A])(f: A => B): List[B]
   */



  def testMap[A, B](list: List[A], f: A => B): List[B] = Nil()
  
  /* c) Write a function that appends one list to another:
   *        def append[A](l: List[A], r: List[A]): List[A]
   */



  def testAppend[A](l: List[A], r: List[A]): List[A] = l

  /* d) Write a function that applies a function to every value of a list:
   *        def flatMap[A, B](list: List[A])(f: A => List[B]): List[B]
   * 
   *    it gets a function which creates a new List[B] for every element of type A in 
   *    the list. Therefore, you generate a List[List[B]]. You have to flatten this 
   *    structure.
   */



  def testFlatMap[A, B](list: List[A], f: A => List[B]): List[B] = Nil()

  /* e) Question: Is it possible to write a tail recursive map function for `Tree`s? If no, why and are you sure :) ? */
}
