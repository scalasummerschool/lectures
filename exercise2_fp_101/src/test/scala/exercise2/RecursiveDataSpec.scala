package exercise2

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

object RecursiveDataSpec extends Properties("recursive data") {

  val listGen = Gen.choose(0, 10).flatMap { length =>
    def generateList(acc: List[Int], l: Int): Gen[List[Int]] = {
      if (l == 0)
        acc
      else for {
        head <- Gen.posNum[Int]
        list <- generateList(Cons(head, acc), l - 1)
      } yield list
    }

    generateList(Nil(), length)
  }

  property("List[Int] head") = forAll(listGen) {
    case Nil()              => RecursiveData.testListIntHead(Nil()) == -1
    case list@Cons(head, _) => RecursiveData.testListIntHead(list) == head
  }

  property("List[Int] tail") = forAll(listGen) {
    case Nil()              => RecursiveData.testListIntTail(Nil()) == Nil()
    case list@Cons(_, tail) => RecursiveData.testListIntTail(list) == tail
  }

  property("List[Int] is empty") = forAll(listGen) {
    case Nil()              => RecursiveData.testListIntEmpty(Nil())
    case list@Cons(_, tail) => !RecursiveData.testListIntEmpty(list)
  }
}
