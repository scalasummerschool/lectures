package exercise2

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.forAll

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

  property("reverse lists") = forAll { list: List[Int] => 
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

  property("map values") = forAll { list: List[Int] => 
    RecursiveFunctions.testMap[Int, String](list, _.toString) == map(list)(_.toString)
  }

  def combine[A](l: List[A], r: List[A]): List[A] = {
    @tailrec
    def loop(lReversedRem: List[A], agg: List[A]): List[A] = lReversedRem match {
      case Nil()         => agg
      case Cons(a, tail) => loop(tail, Cons(a, agg))
    }

    loop(reverse(l), r)
  }

  property("combine lists") = forAll { (l: List[Int], r: List[Int]) =>
    RecursiveFunctions.testCombine(l, r) == combine(l, r)
  }

  def take[A](n: Int, as: List[A]): List[A] = {
    if (n < 0)
      Nil()
    else if (n >= RecursiveFunctions.length(as))
      as
    else {
      @tailrec
      def loop(rem: List[A], agg: List[A]): List[A] = rem match {
        case Nil()         => Nil()
        case Cons(a, tail) => 
          val updatedAgg = Cons(a, agg)

          if (RecursiveFunctions.length(updatedAgg) == n) 
            reverse(updatedAgg)
          else
            loop(tail, updatedAgg)
      }

      loop(as, Nil())
    }
  }

  property("take first n elements") = forAll { (n: Int, as: List[Int]) =>
    RecursiveFunctions.testTake(n, as) == take(n, as)
  }
}
