package exercise2

object RecursiveDataSolution {

  def isListEmpty(list: List[Int]): Boolean = list match {
    case Cons(_, _) => false
    case Nil()      => true
  }

  def listHead(list: List[Int]): Int = list match {
    case Cons(head, _) => head
    case Nil()         => -1
  }

  sealed trait Tree[A]
  case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
  case class Leaf[A](a: A)                          extends Tree[A]

  val tree = Node(Node(Leaf(0), Leaf(1)), Leaf(2))
}
