package exercise2

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop._

import scala.annotation.tailrec

object RecursiveFunctionsSpec extends Properties("recursive functions") {

  implicit val listGen = Arbitrary[List[Int]] { 
    Gen.choose(0, 10).flatMap { length =>
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
  }

  val replicationGen = for {
    n <- Gen.choose(0, 10)
    a <- arbitrary[String]
  } yield (n, a)

  property("reverse lists") = forAll { list: List[Int] => 
    RecursiveFunctions.testReverse(list) =? RecursiveFunctionsSolution.reverse(list)
  }

  property("map values") = forAll { list: List[Int] => 
    RecursiveFunctions.testMap[Int, String](list, _.toString) =? RecursiveFunctionsSolution.map(list)(_.toString)
  }

  property("append lists") = forAll { (l: List[Int], r: List[Int]) =>
    RecursiveFunctions.testAppend(l, r) =? RecursiveFunctionsSolution.append(l, r)
  }

  property("flatMap values") = forAll { list: List[Int] => 
    RecursiveFunctions.testFlatMap[Int, String](list, a => Cons(a.toString, Nil())) =? RecursiveFunctionsSolution.flatMap(list)(a => Cons(a.toString, Nil()))
  }
}
