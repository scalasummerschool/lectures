package exercise2

sealed trait List[A]
case class Cons[A](head: A, tail: List[A]) extends List[A]
case class Nil[A]() extends List[A]

/** Write your solutions as stand-alone functions. */
object RecursiveData {

  // a) Implement a function which determines if a `List[Int]` is empty or not.



  def testListIntEmpty(list: List[Int]): Boolean = false

  // b) Implement a function which gets the head of a `List[Int]` or returns -1 if empty.



  def testListIntHead(list: List[Int]): Int = 0

  // c) Can we change `List[A]` to guarantee to be not-empty?


  /* d) Implement a generic Tree which has its values in the leafs and consists of:
   *      node - left and right Tree
   *      leaf - a value of type A
   */

}
