package exercise3

import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

import scala.annotation.tailrec

object SequenceSpec extends Properties("sequence") {

  @tailrec
  def lastElement[A](seq: Seq[A]): Option[A] = seq match {
    case last :: Nil => Some(last)
    case _ :: tail   => lastElement(tail)
    case Nil         => None
  }

  property("last element") = forAll { seq: Seq[Int] =>
    Sequence.testLastElement(seq) == lastElement(seq)
  }

  def reverse[A](seq: Seq[A]): Seq[A] = seq.foldLeft(Seq.empty[A])((agg, a) => a +: agg)

  property("reverse") = forAll { seq: Seq[Int] =>
    Sequence.testReverse(seq) == reverse(seq)
  }

  @tailrec
  def zip[A](a: Seq[A], b: Seq[A], agg: Seq[(A, A)] = Nil): Seq[(A, A)] = (a, b) match {
    case (ah +: at, bh +: bt) => zip(at, bt, (ah, bh) +: agg)
    case (Nil, _) | (_, Nil)  => reverse(agg)
  }

  property("zip") = forAll { (a: Seq[Int], b: Seq[Int]) =>
    Sequence.testZip(a, b) == zip(a, b)
  }

  def forAllElements[A](seq: Seq[A])(cond: A => Boolean): Boolean = seq.foldLeft(true)((agg, a) => agg && cond(a))

  property("for all") = forAll { seq: Seq[Int] =>
    Sequence.testForAll(seq)(_ > 3) == forAllElements(seq)(_ > 3)
  }

  def palindrom[A](seq: Seq[A]): Boolean = {
    val middle = seq.length / 2 + 1

    if (seq.isEmpty) 
      true
    else
      forAllElements(zip(seq.take(middle), reverse(seq).take(middle)))(pair => pair._1 == pair._2)
  }

  property("palindrom") = forAll(palindromGen) { seq =>
    Sequence.testPalindrom(seq) == palindrom(seq)
  }

  def deduplicate[A](seq: Seq[A]): Seq[A] = reverse(seq.foldLeft(Seq.empty[A]) { (agg, a) =>
    if (agg.contains(a)) agg
    else                 a +: agg
  })

  property("deduplicate") = forAll { seq: Seq[Int] =>
    Sequence.testDeduplicate(seq) == deduplicate(seq)
  }

  def flatMap[A, B](seq: Seq[A])(f: A => Seq[B]): Seq[B] = reverse(seq.foldLeft(Seq.empty[B]) { (agg, a) =>
    val bs = f(a)

    bs ++ agg
  })

  property("flatMap") = forAll { seq: Seq[Int] =>
    Sequence.testFlatMap(seq)(a => Seq(a, a + 1, a + 2)) == flatMap(seq)(a => Seq(a, a + 1, a + 2))
  }

  val palindromGen = for {
    isPalindrom <- Gen.oneOf(true, false)
    seq         <- Gen.sequence[Seq[Int], Int](List(Gen.posNum[Int]))
  } yield {
    if (isPalindrom) {
      val half = seq.take(seq.length / 2)

      half ++ half.reverse
    }
    else
      seq
  }
}
