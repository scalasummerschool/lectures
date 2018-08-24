package exercise2

sealed trait List[+A]

case class Cons[+A](head: A, tail: List[A]) extends List[A]
case object Nil extends List[Nothing]

object RecursiveData {

  // a) implement a function which determines if a `List[Int]` is empty or not



  def testListIntEmpty(list: List[Int]): Boolean = false

  // b) implement a function which gets the head of a `List[Int]` or returns -1 if empty



  def testListIntHead(list: List[Int]): Int = 0

  // c) implement a function which gets the tail of a `List[Int]`



  def testListIntTail(list: List[Int]): List[Int] = list

  // d) can we change `List[A]` to guarantee to be not-empty?

}

/* e) Implement a generic Tree which has its values in the leaves and concised of:
 *      node - left and right Tree
 *      leaf - a value of type A
 */
