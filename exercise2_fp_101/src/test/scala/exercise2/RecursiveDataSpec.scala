package exercise2

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop._

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

  property("List[Int] head") = forAll(listGen) { list =>
    RecursiveData.testListIntHead(list) =? RecursiveDataSolution.listHead(list)
  }

  property("List[Int] is empty") = forAll(listGen) { list =>
    RecursiveData.testListIntEmpty(list) =? RecursiveDataSolution.isListEmpty(list)
  }
}
