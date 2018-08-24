package exercise2

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.forAll

import scala.annotation.tailrec

object RecursiveFunctionsSpec extends Properties("recursive functions") {

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

  val replicationGen = for {
    n <- Gen.choose(0, 10)
    a <- arbitrary[String]
  } yield (n, a)

  property("replicate value") = forAll(replicationGen) { case (n, a) => 
    RecursiveFunctions.testReplicate(n, a) == {
      @tailrec
      def loop(agg: List[String], rem: Int): List[String] = {
        if (rem == 0) agg
        else          loop(Cons(a, agg), rem - 1)
      }

      loop(Nil(), n)
    }
  }

  def reverse[A](list: List[A]): List[A] = {
    @tailrec
    def loop(rem: List[A], agg: List[A]): List[A] = rem match {
      case Nil()         => agg
      case Cons(a, tail) => loop(tail, Cons(a, agg))
    }

    loop(list, Nil())
  }

  property("reverse lists") = forAll(listGen) { list => 
    RecursiveFunctions.testReverse(list) == reverse(list)
  }

  def map[A, B](list: List[A])(f: A => B): List[B] = {
    @tailrec
    def loop(rem: List[A], agg: List[B]): List[B] = rem match {
      case Nil()         => agg
      case Cons(a, tail) => loop(tail, Cons(f(a), agg))
    }

    reverse(loop(list, Nil()))
  }

  property("map values") = forAll(listGen) { list => 
    RecursiveFunctions.testMap[Int, String](list, _.toString) == map(list)(_.toString)
  }
}
