package exercise2

import scala.annotation.tailrec

// REMARK: try to make all functions tail recursive; use the annotation to proof it
object RecursiveFunctions {

  def length[A](as: List[A]): Int = {
    @tailrec
    def loop(rem: List[A], agg: Int): Int = rem match {
      case Cons(_, tail) => loop(tail, agg + 1)
      case Nil()         => agg
    }

    loop(as, 0)
  }

  /* a) write a function that replicates a value n-times to create a list:
   *        def replicate[A](n: Int, a: A): List[A]
   */



  def testReplicate[A](n: Int, a: A): List[A] = Nil()

  /* b) write a function that reverses a list:
   *        def reverse[A](list: List[A]): List[A]
   */



  def testReverse[A](list: List[A]): List[A] = list

  /* c) write a function that applies a function to every value of a list:
   *        def map[A, B](list: List[A])(f: A => B): List[B]
   */



  def testMap[A, B](list: List[A], f: A => B): List[B] = Nil()
  
  /* d) write a function that combines two lists:
   *        def combine[A](l: List[A], r: List[A]): List[A]
   */



  def testCombine[A](l: List[A], r: List[A]): List[A] = l

  /* e) write a function which takes the first n elements:
   *        def take[A](n: Int, as: List[A]): List[A]
   *    return `as` when n >= length(as) and Nil if n < 0
   */



  def testTake[A](n: Int, as: List[A]): List[A] = as

  /* f) Is it possible to write a tail recursive map function for `Tree`s? If no, why? */

  /* g) Is it possible to write a tail recursive function to calculate the Fibonacci numbers? If yes, how?
   *    Fibonacci: n is Natural Number
   *       if n == 1 or n == 2 then return 1
   *       else return fibonacci for (n - 1) + fibunacci for (n - 2)
   */
}
